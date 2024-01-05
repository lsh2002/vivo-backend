//package com.lsh.vivo.mq;
//
//import com.lsh.vivo.service.SeckillGoodsService;
//import com.lsh.vivo.constant.RedisKey;
//import com.rabbitmq.client.Channel;
//import lombok.RequiredArgsConstructor;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.LocalDateTime;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author lsh
// * @since 2023-09-09 15:43
// */
//@Component
//@RequiredArgsConstructor
//public class SeckillOrderQueue {
//
//    private final OrderService orderService;
//    private final SeckillGoodsService seckillGoodsService;
//    private final StringRedisTemplate stringRedisTemplate;
//
//    @RabbitListener(queues = "seckill_order")
//    public void insertOrder(Map<String, Object> map, Channel channel, Message message) {
//
//        // 查看id，保证幂等性
//        String correlationId = message.getMessageProperties().getCorrelationId();
//        if (Boolean.FALSE.equals(stringRedisTemplate.hasKey(RedisKey.SECKILL_RABBITMQ_ID + correlationId))) {
//            // redis中存在，表明此条消息已消费，请勿重复消费
//            return;
//        }
//        String seckillId = (String) map.get("seckillId");
//        String userId = (String) map.get("userId");
//        // 存入redis，因为只需要判断是否存在，因此value为多少无所谓
//        stringRedisTemplate.opsForValue().set(RedisKey.SECKILL_RABBITMQ_ID + correlationId, "1");
//        Long seckillEndTime = seckillGoodsService.getEndTime(seckillId);
//        stringRedisTemplate.expire(RedisKey.SECKILL_RABBITMQ_ID + correlationId, seckillEndTime - new LocalDateTime().getTime(), TimeUnit.SECONDS); // 设置过期时间
//
//        try {
//            orderService.saveSeckillOrder(seckillId, userId);
//        } catch (Exception e) {
//            e.printStackTrace();
//            try {
//                stringRedisTemplate.delete(RedisKey.SECKILL_RABBITMQ_ID + correlationId);
//                // 将该消息放入队列尾部，尝试再次消费
//                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        }
//    }
//}
