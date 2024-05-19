package com.lsh.vivo.controller;

import com.lsh.vivo.bean.request.order.OrderSaveVO;
import com.lsh.vivo.entity.Order;
import com.lsh.vivo.entity.OrderItem;
import com.lsh.vivo.enumerate.BaseResultCodeEnum;
import com.lsh.vivo.exception.BaseRequestErrorException;
import com.lsh.vivo.mapper.struct.OrderMpp;
import com.lsh.vivo.mq.MQSender;
import com.lsh.vivo.redis.SeckillKey;
import com.lsh.vivo.service.OrderSeckillService;
import com.lsh.vivo.service.OrderService;
import com.lsh.vivo.util.RedisUtil;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import static com.lsh.vivo.entity.table.OrderSeckillTableDef.ORDER_SECKILL;
import static com.lsh.vivo.entity.table.OrderTableDef.ORDER;
import static com.mybatisflex.core.query.QueryMethods.number;
import static com.mybatisflex.core.query.QueryMethods.select;

/**
 * @author lsh
 * @since 2023-08-19 13:25
 */
@RestController
@RequestMapping("/seckill")
@RequiredArgsConstructor
public class OrderSeckillController {

    private final RedisUtil redisUtil;
    private final OrderSeckillService orderSeckillService;
    private final MQSender sender;
    private final OrderService orderService;

    @PostMapping
    public Order saveSeckillOrder(@NotBlank @RequestBody OrderSaveVO orderSaveVO) {
        Order order = OrderMpp.INSTANCE.toDO(orderSaveVO);
        OrderItem orderItem = order.getOrderItems().get(0);
        // 判断是否重复操作，已经成功秒杀，不能再次秒杀
        QueryWrapper queryWrapper = select(number(1))
                .where(ORDER_SECKILL.USER_ID.eq(order.getUserId()))
                .and(ORDER_SECKILL.SECKILL_ID.eq(order.getSeckillId()));
        boolean exists = orderSeckillService.exists(queryWrapper);
        if (exists) {
            throw new BaseRequestErrorException(BaseResultCodeEnum.ERROR_OUT_OF_USER_SECKILL_COUNT);
        }
        // 可秒杀数量 预减
        long count = redisUtil.decr("seckill:" + orderItem.getSkuId() + ":", 1);
        if (count < 0) {
            redisUtil.incr("seckill:" + orderItem.getSkuId() + ":", 1);
            throw new BaseRequestErrorException(BaseResultCodeEnum.ERROR_GOODS_SKU_STOCK);
        }
        // 库存预减
        long count1 = redisUtil.decr("goodsSku:" + orderItem.getSkuId() + ":", 1);
        if (count1 < 0) {
            redisUtil.incr("goodsSku:" + orderItem.getSkuId() + ":", 1);
            throw new BaseRequestErrorException(BaseResultCodeEnum.ERROR_GOODS_SKU_STOCK);
        }
        // 秒杀
        return orderService.seckill(order);
    }

    @GetMapping("/result")
    public Order seckillResult(@NotBlank String seckillId, @NotBlank String userId) {
        QueryWrapper queryWrapper = select(ORDER_SECKILL.ORDER_ID)
                .where(ORDER_SECKILL.USER_ID.eq(userId))
                .and(ORDER_SECKILL.SECKILL_ID.eq(seckillId));
        String orderId = orderSeckillService.getOneAs(queryWrapper, String.class);
        if (StringUtils.isBlank(orderId)) {
            // 查看秒杀商品是否已经结束
            Boolean result = (Boolean) redisUtil.get(SeckillKey.isGoodsOver + "" + seckillId);
            if (Boolean.TRUE.equals(result)) {
                return null;
            }
        }
        QueryWrapper queryWrapper1 = select()
                .where(ORDER.ID.eq(orderId))
                .limit(1);
        return orderService.getOne(queryWrapper1);
    }
}
