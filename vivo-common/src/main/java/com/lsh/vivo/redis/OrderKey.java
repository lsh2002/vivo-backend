package com.lsh.vivo.redis;

/**
 * @program: SeckillProject
 * @description: 订单key
 **/
public class OrderKey extends BasePrefix {
    public OrderKey(String prefix) {
        super(prefix);
    }

    //获取订单键值
    public static OrderKey getOrderByUidOid = new OrderKey("ok");
}
