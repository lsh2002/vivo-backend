package com.lsh.vivo.service.impl;

import com.lsh.vivo.entity.OrderSeckill;
import com.lsh.vivo.mapper.OrderSeckillMapper;
import com.lsh.vivo.service.OrderSeckillService;
import com.lsh.vivo.service.system.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;

import static com.lsh.vivo.entity.table.OrderSeckillTableDef.ORDER_SECKILL;

@Service
public class OrderSeckillServiceImpl extends CommonServiceImpl<OrderSeckillMapper, OrderSeckill>
        implements OrderSeckillService {
    @Override
    public OrderSeckill getByUserIdAndSeckillId(String userId, String seckillId) {
        return queryChain().select()
                .where(ORDER_SECKILL.USER_ID.eq(userId))
                .and(ORDER_SECKILL.SECKILL_ID.eq(seckillId))
                .one();
    }
}
