package com.lsh.vivo.mq;

import com.lsh.vivo.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

/**
 * @program: SeckillProject
 * @description: 消息队列：发送
 **/
@Service
@RequiredArgsConstructor
public class MQSender {
    private static Logger log = LoggerFactory.getLogger(MQSender.class);

    private final AmqpTemplate amqpTemplate;

    /**
     * 秒杀业务消息发送
     */
    public void sendSeckillMessage(SeckillMessage sm) {
        String msg = BeanUtil.beanToString(sm);
        log.info("消息队列 MQSender 秒杀业务消息发送 send message:" + msg);
        amqpTemplate.convertAndSend(MQConfig.SECKILL_QUEUE, msg);
    }

    /**
     * 字符串发送
     *
     * @param message
     */
    public void sendStr(Object message) {
        String msg = BeanUtil.beanToString(message);
        log.info("消息队列 MQSender 字符串发送 send message:" + msg);
        amqpTemplate.convertAndSend(MQConfig.QUEUE, msg);
    }

    /**
     * Topic模式 交换机Exchange
     */
    public void sendTopic(Object message) {
        String msg = BeanUtil.beanToString(message);
        log.info("消息队列 MQSender Topic模式交换机 send topic message:" + msg);
        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key1", msg + "1");
        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key2", msg + "2");
    }
}

