package com.lsh.vivo.service.impl;

import com.lsh.vivo.entity.OrderItem;
import com.lsh.vivo.mapper.OrderItemMapper;
import com.lsh.vivo.service.OrderItemService;
import com.lsh.vivo.service.system.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;

import static com.lsh.vivo.entity.table.OrderItemTableDef.ORDER_ITEM;

@Service
public class OrderItemServiceImpl extends CommonServiceImpl<OrderItemMapper, OrderItem>
        implements OrderItemService {
    @Override
    public OrderItem getByUserIdAndGoodsId(String userId, String skuId) {
        return queryChain()
                .from(ORDER_ITEM)
                .where(ORDER_ITEM.USER_ID.eq(userId))
                .and(ORDER_ITEM.SKU_ID.eq(skuId))
                .one();
    }
}
