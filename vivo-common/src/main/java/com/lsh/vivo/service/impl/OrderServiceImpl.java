package com.lsh.vivo.service.impl;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONObject;
import com.lsh.vivo.bean.dto.order.OrderConditionDTO;
import com.lsh.vivo.bean.dto.order.OrderDataDTO;
import com.lsh.vivo.entity.Order;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.enumerate.OrderStatusEnum;
import com.lsh.vivo.enumerate.ServiceTypeEnum;
import com.lsh.vivo.enumerate.StockStatusEnum;
import com.lsh.vivo.event.order.bean.OrderSaveEvent;
import com.lsh.vivo.mapper.OrderMapper;
import com.lsh.vivo.service.GoodsSkuService;
import com.lsh.vivo.service.OrderService;
import com.lsh.vivo.service.system.impl.CommonServiceImpl;
import com.lsh.vivo.util.MapperStructTypeConvert;
import com.lsh.vivo.util.Snowflake;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.If;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.lsh.vivo.entity.table.GoodsSkuTableDef.GOODS_SKU;
import static com.lsh.vivo.entity.table.OrderTableDef.ORDER;
import static com.mybatisflex.core.query.QueryMethods.*;

/**
 * @author ASUS
 * @description 针对表【order(订单)】的数据库操作Service实现
 * @createLocalDateTime 2023-10-28 22:51:47
 */
@Service
public class OrderServiceImpl extends CommonServiceImpl<OrderMapper, Order>
        implements OrderService {

    private Snowflake snowflake;

    @Resource
    private MapperStructTypeConvert mapperStructTypeConvert;

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Resource
    private GoodsSkuService goodsSkuService;

    @Autowired
    public void setSnowflake(Snowflake snowflake) {
        this.snowflake = snowflake;
    }

    @Override
    public List<Order> listLastOrder(String userId) {
        return queryChain()
                .from(ORDER)
                .where(ORDER.USER_ID.eq(userId))
                .and(ORDER.STATUS.ne(OrderStatusEnum.T.name()))
                .orderBy(ORDER.PAY_TIME.desc())
                .limit(5)
                .list();
    }

    @Override
    public List<Order> listOrder(String userId, String status) {
        QueryWrapper queryWrapper = select()
                .from(ORDER)
                .where(ORDER.USER_ID.eq(userId))
                .and(ORDER.STATUS.eq(status, If::hasText))
                .and(ORDER.STATUS.ne(OrderStatusEnum.T.name()))
                .orderBy(ORDER.PAY_TIME.desc());
        return mapper.selectListWithRelationsByQuery(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(List<String> orderIds, OrderStatusEnum orderStatusEnum, Long time) {
        String status = orderStatusEnum == null ? "" : orderStatusEnum.name();
        updateChain()
                .set(ORDER.STATUS, status, If::hasText)
                .set(ORDER.PAY_TIME, mapperStructTypeConvert.longToLocalDateTime(time), OrderStatusEnum.S.name().equals(status))
                .set(ORDER.CANCEL_TIME, mapperStructTypeConvert.longToLocalDateTime(time), OrderStatusEnum.C.name().equals(status))
                .set(ORDER.FINISH_TIME, mapperStructTypeConvert.longToLocalDateTime(time), OrderStatusEnum.F.name().equals(status))
                .where(ORDER.ORDER_ID.in(orderIds))
                .and(ORDER.STATUS.ne(status, If::hasText))
                .update();

        List<Order> list = queryChain().select(ORDER.SKU_ID, ORDER.NUM)
                .where(ORDER.ORDER_ID.in(orderIds))
                .list();
        if (OrderStatusEnum.F.name().equals(status)) {
            list.forEach(order -> {
                 goodsSkuService.updateChain()
                        .set(GOODS_SKU.SALES, GOODS_SKU.SALES.add(order.getNum()))
                        .where(GOODS_SKU.ID.eq(order.getSkuId()))
                        .update();
            });
        }

    }

    @Override
    public int countUnpaid(String userId) {
        return (int) queryChain()
                .select(ORDER.ID)
                .where(ORDER.USER_ID.eq(userId))
                .and(ORDER.STATUS.eq(OrderStatusEnum.P.name()))
                .count();

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveBatch(Collection<Order> entities, boolean cart) {
        for (Order entity : entities) {
            entity.setOrderId(DateTime.now().year() + snowflake.nextIdStr());
            entity.setStatus(OrderStatusEnum.P.name());
            goodsSkuService.updateStock(entity.getSkuId(), entity.getNum(), StockStatusEnum.O);
        }
        super.saveBatch(entities);

        if (cart) {
            String userId = entities.stream().map(Order::getUserId).toList().get(0);
            List<String> skuIds = entities.stream().map(Order::getSkuId).toList();
            OrderSaveEvent orderSaveEvent = new OrderSaveEvent(this);
            orderSaveEvent.setSkuIds(skuIds);
            orderSaveEvent.setUserId(userId);
            applicationEventPublisher.publishEvent(orderSaveEvent);
        }
        return true;
    }

    @Override
    public Page<Order> page(Page<Order> page, OrderConditionDTO orderConditionDTO) {
        return queryChain()
                .from(ORDER)
                .leftJoin(GOODS_SKU).on(ORDER.SKU_ID.eq(GOODS_SKU.ID))
                .where(ORDER.ORDER_ID.likeLeft(orderConditionDTO.getOrderId(), If::hasText))
                .and(ORDER.STATUS.eq(orderConditionDTO.getStatus(), If::hasText))
                .and(ORDER.SERVICE_TYPE.eq(orderConditionDTO.getServiceTypeEnum(), If::hasText))
                .and(ORDER.RECEIVER_NAME.like(orderConditionDTO.getReceiverName(), If::hasText))
                .and(ORDER.RECEIVER_PHONE.like(orderConditionDTO.getReceiverPhone(), If::hasText))
                .and(ORDER.COURIER_NUMBER.likeLeft(orderConditionDTO.getCourierNumber(), If::hasText))
                .and(GOODS_SKU.NAME.like(orderConditionDTO.getName(), If::hasText))
                .and(ORDER.STATUS.ne(CommonStatusEnum.T.name()))
                .orderBy(ORDER.PAY_TIME.desc())
                .page(page);
    }

    @Override
    public JSONObject getTodayData() {
        JSONObject result = new JSONObject();
        // 查询今天的记录
        LocalDateTime start = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime end = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);

        // 今日订单数
        long count = queryChain()
                .select(ORDER.ORDER_ID)
                .from(ORDER)
                .where(ORDER.ORDER_TIME.between(start, end))
                .count();
        result.put("count", Optional.of(count));
        // 昨日订单数
        long ycount = queryChain()
                .select(sum(ORDER.ORDER_ID))
                .from(ORDER)
                .where(ORDER.ORDER_TIME.between(start.minusDays(1), end.minusDays(1)))
                .count();
        if (ycount != 0) {
            double number = (double) (count - ycount) / ycount * 100;
            // 保留2位小数
            result.put("countThany", Math.round(number * 100) / 100.0);
        }

        // 今日成交额
        Double gmv = queryChain()
                .select(sum(ORDER.NUM.multiply(ORDER.PRICE)))
                .from(ORDER)
                .where(ORDER.ORDER_TIME.between(start, end))
                .oneAsOpt(Double.class)
                .orElse((double) 0);
        result.put("gmv", gmv);
        // 昨日成交额
        Double ygmv = queryChain()
                .select(sum(ORDER.NUM.multiply(ORDER.PRICE)))
                .from(ORDER)
                .where(ORDER.ORDER_TIME.between(start.minusDays(1), end.minusDays(1)))
                .oneAsOpt(Double.class)
                .orElse((double) 0);
        if (ygmv != 0) {
            double number = (gmv - ygmv) / ygmv * 100;
            result.put("gmvThany", Math.round(number * 100) / 100.0);
        }

        // 今日销售额
        Double gms = queryChain()
                .select(sum(ORDER.NUM.multiply(ORDER.PRICE)))
                .from(ORDER)
                .where(ORDER.FINISH_TIME.between(start, end))
                .and(ORDER.STATUS.ne(OrderStatusEnum.C.name()))
                .oneAsOpt(Double.class)
                .orElse((double) 0);
        result.put("gms", gms);
        // 昨日销售额
        Double ygms = queryChain()
                .select(sum(ORDER.NUM.multiply(ORDER.PRICE)))
                .from(ORDER)
                .where(ORDER.FINISH_TIME.between(start.minusDays(1), end.minusDays(1)))
                .oneAsOpt(Double.class)
                .orElse((double) 0);
        if (ygms != 0) {
            double number = (gms - ygms) / ygms * 100;
            result.put("gmsThany", Math.round(number * 100) / 100.0);
        }

        // 待发货数
        long dfs = queryChain()
                .select(ORDER.ORDER_ID)
                .from(ORDER)
                .and(ORDER.STATUS.eq(OrderStatusEnum.S.name()))
                .count();
        result.put("dfs", Optional.of(dfs));
        return result;
    }

    @Override
    public JSONObject getMonthData() {
        JSONObject result = new JSONObject();
        // 查询本月的记录
        LocalDateTime start = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime end = LocalDateTime.now().withDayOfMonth(LocalDate.now().lengthOfMonth()).withHour(23).withMinute(59).withSecond(59);

        // 本月订单数
        long count = queryChain()
                .select(ORDER.ORDER_ID)
                .from(ORDER)
                .where(ORDER.ORDER_TIME.between(start, end))
                .count();
        result.put("count", Optional.of(count));
        // 上月订单数
        long lcount = queryChain()
                .select(sum(ORDER.ORDER_ID))
                .from(ORDER)
                .where(ORDER.ORDER_TIME.between(start.minusMonths(1), end.minusMonths(1)))
                .count();
        if (lcount != 0L) {
            double number = (double) ((count - lcount) / lcount * 100);
            result.put("countThanl", Math.round(number * 100) / 100.0);
        }

        // 本月成交额
        Double gmv = queryChain()
                .select(sum(ORDER.NUM.multiply(ORDER.PRICE)))
                .from(ORDER)
                .where(ORDER.ORDER_TIME.between(start, end))
                .oneAsOpt(Double.class)
                .orElse((double) 0);
        result.put("gmv", gmv);
        // 上月成交额
        Double lgmv = queryChain()
                .select(sum(ORDER.NUM.multiply(ORDER.PRICE)))
                .from(ORDER)
                .where(ORDER.ORDER_TIME.between(start.minusMonths(1), end.minusMonths(1)))
                .oneAsOpt(Double.class)
                .orElse((double) 0);
        if (lgmv != 0D) {
            double number = (gmv - lgmv) / lgmv * 100;
            result.put("gmvThanl", Math.round(number * 100) / 100.0);
        }

        // 本月销售额
        Double gms = queryChain()
                .select(sum(ORDER.NUM.multiply(ORDER.PRICE)))
                .from(ORDER)
                .where(ORDER.FINISH_TIME.between(start, end))
                .and(ORDER.STATUS.ne(OrderStatusEnum.C.name()))
                .oneAsOpt(Double.class)
                .orElse((double) 0);
        result.put("gms", gms);
        // 上月销售额
        Double lgms = queryChain()
                .select(sum(ORDER.NUM.multiply(ORDER.PRICE)))
                .from(ORDER)
                .where(ORDER.FINISH_TIME.between(start.minusMonths(1), end.minusMonths(1)))
                .oneAsOpt(Double.class)
                .orElse((double) 0);
        if (lgms != 0D) {
            double number = (gms - lgms) / lgms * 100;
            result.put("gmsThany", Math.round(number * 100) / 100.0);
        }
        return result;
    }

    @Override
    public JSONObject getYearData() {
        JSONObject result = new JSONObject();
        // 获取每个月的销售额
        List<OrderDataDTO> orderDataDTOS1 = queryChain()
                .select(month(ORDER.FINISH_TIME).as("month"), sum(ORDER.NUM.multiply(ORDER.PRICE)).as("sum"))
                .from(ORDER)
                .where(ORDER.FINISH_TIME.between(LocalDateTime.now().withDayOfYear(1).withHour(0).withMinute(0).withSecond(0), LocalDateTime.now().withDayOfYear(LocalDate.now().lengthOfYear()).withHour(23).withMinute(59).withSecond(59)))
                .and(ORDER.STATUS.eq(OrderStatusEnum.F.name()))
                .groupBy(ORDER.FINISH_TIME)
                .listAs(OrderDataDTO.class);
        result.put("gms", orderDataDTOS1);

        // 获取每个月的成交额
        List<OrderDataDTO> orderDataDTOS2 = queryChain()
                .select(month(ORDER.ORDER_TIME).as("month"), sum(ORDER.NUM.multiply(ORDER.PRICE)).as("sum"))
                .from(ORDER)
                .where(ORDER.ORDER_TIME.between(LocalDateTime.now().withDayOfYear(1).withHour(0).withMinute(0).withSecond(0), LocalDateTime.now().withDayOfYear(LocalDate.now().lengthOfYear()).withHour(23).withMinute(59).withSecond(59)))
                .groupBy(ORDER.ORDER_TIME)
                .listAs(OrderDataDTO.class);
        result.put("gmv", orderDataDTOS2);
        return result;
    }

    @Override
    public List<Order> listRefundOrder(String userId) {
        QueryWrapper queryWrapper = select()
                .where(ORDER.USER_ID.eq(userId))
                .and(ORDER.SERVICE_TYPE.in(ServiceTypeEnum.listServiceType()))
                .orderBy(ORDER.SERVICE_TIME.desc());
        return mapper.selectListWithRelationsByQuery(queryWrapper);
    }
}




