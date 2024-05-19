package com.lsh.vivo.mq;

import com.lsh.vivo.entity.Order;
import lombok.Getter;
import lombok.Setter;

/**
 * @program: SeckillProject
 * @description: 秒杀消息封装
 **/
@Getter
@Setter
public class SeckillMessage {
    private Order order;
}
