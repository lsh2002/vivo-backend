package com.lsh.vivo.mq;

import com.lsh.vivo.entity.GoodsSku;
import com.lsh.vivo.entity.Order;
import com.lsh.vivo.entity.OrderItem;
import com.lsh.vivo.entity.OrderSeckill;
import com.lsh.vivo.service.*;
import com.lsh.vivo.util.BeanUtil;
import com.lsh.vivo.util.RedisUtil;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.lsh.vivo.entity.table.GoodsSeckillTableDef.GOODS_SECKILL;
import static com.mybatisflex.core.query.QueryMethods.select;

/**
 * @program: SeckillProject
 * @description: 消息队列：接收
 **/
@Service
public class MQReceiver {
    private static Logger log = LoggerFactory.getLogger(MQReceiver.class);

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    GoodsSkuService goodsSkuService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    GoodsSeckillService seckillService;
    @Resource
    OrderSeckillService orderSeckillService;
    @Resource
    OrderService orderService;

    /**
     * 秒杀业务消息接收
     *
     * @param message
     */
    @RabbitListener(queues = MQConfig.SECKILL_QUEUE)
    public void receive(String message) {
        log.info("receive message:" + message);
        SeckillMessage sm;
        try {
            sm = BeanUtil.stringToBean(message, SeckillMessage.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return;
        }
        if (sm == null) {
            return;
        }
        Order order = sm.getOrder();
        if (order == null) {
            return;
        }
        OrderItem orderItem = order.getOrderItems().get(0);
        // 判断秒杀商品数量
        QueryWrapper querySeckill = select(GOODS_SECKILL.SECKILL_NUM)
                .where(GOODS_SECKILL.SKU_ID.eq(orderItem.getSkuId()));
        Integer num = seckillService.getOneAs(querySeckill, Integer.class);
        if (num <= 0) {
            return;
        }

        GoodsSku goodsSku = goodsSkuService.getById(orderItem.getSkuId());
        int stock = goodsSku.getStock();
        if (stock <= 0) {
            return;
        }
        // 判断是否已经秒杀到了
        OrderSeckill orderSeckill = orderSeckillService.getByUserIdAndSeckillId(order.getUserId(), order.getSeckillId());
        if (orderSeckill != null) {
            return;
        }
        //减库存 下订单 写入秒杀订单
        orderService.seckill(order);
    }

    /**
     * 简单字符串接收测试
     *
     * @param message
     */
    @RabbitListener(queues = MQConfig.QUEUE)
    public void receiveStr(String message) {
        log.info("receive message:" + message);
    }

    /**
     * Topic模式 交换机Exchange  queue1
     */
    @RabbitListener(queues = MQConfig.TOPIC_QUEUE1)
    public void receiveTopic1(String message) {
        log.info(" topic  queue1 message:" + message);
    }

    /**
     * Topic模式 交换机Exchange  queue1
     */
    @RabbitListener(queues = MQConfig.TOPIC_QUEUE2)
    public void receiveTopic2(String message) {
        log.info(" topic  queue2 message:" + message);
    }
}
