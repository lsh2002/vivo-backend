package com.lsh.vivo.service;

import com.lsh.vivo.entity.OrderSeckill;
import com.lsh.vivo.service.system.CommonService;

/**
 * @author ASUS
 */
public interface OrderSeckillService extends CommonService<OrderSeckill> {

    OrderSeckill getByUserIdAndSeckillId(String userId, String seckillId);
}
