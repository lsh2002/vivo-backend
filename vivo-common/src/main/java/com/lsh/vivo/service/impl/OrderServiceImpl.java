package com.lsh.vivo.service.impl;

import cn.hutool.core.date.DateTime;
import com.lsh.vivo.entity.Order;
import com.lsh.vivo.enumerate.OrderStatusEnum;
import com.lsh.vivo.mapper.OrderMapper;
import com.lsh.vivo.service.OrderService;
import com.lsh.vivo.service.system.impl.CommonServiceImpl;
import com.lsh.vivo.util.MapperStructTypeConvert;
import com.lsh.vivo.util.Snowflake;
import com.mybatisflex.core.query.If;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static com.lsh.vivo.entity.table.OrderTableDef.ORDER;
import static com.mybatisflex.core.query.QueryMethods.select;

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
    public void updateStatus(List<String> orderIds, OrderStatusEnum orderStatusEnum, Long time) {
        String status = orderStatusEnum == null ? "" : orderStatusEnum.name();
        updateChain()
                .set(ORDER.STATUS, status, If::hasText)
                .set(ORDER.PAY_TIME, mapperStructTypeConvert.longToLocalDateTime(time), OrderStatusEnum.S.name().equals(status))
                .set(ORDER.CANCEL_TIME, mapperStructTypeConvert.longToLocalDateTime(time), OrderStatusEnum.C.name().equals(status))
                .where(ORDER.ORDER_ID.in(orderIds))
                .and(ORDER.STATUS.ne(status, If::hasText))
                .update();
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
    public boolean saveBatch(Collection<Order> entities) {
        for (Order entity : entities) {
            entity.setOrderId(DateTime.now().year() + snowflake.nextIdStr());
            entity.setStatus(OrderStatusEnum.P.name());
        }
        return super.saveBatch(entities);
    }
}




