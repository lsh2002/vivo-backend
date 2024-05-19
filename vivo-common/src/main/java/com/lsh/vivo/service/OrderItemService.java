package com.lsh.vivo.service;

import com.lsh.vivo.entity.OrderItem;
import com.lsh.vivo.service.system.CommonService;

/**
 * @author ASUS
 */
public interface OrderItemService extends CommonService<OrderItem> {

    /**
     * 根据用户id和商品id查询订单
     *
     * @param userId
     * @param skuId
     * @return
     */
    OrderItem getByUserIdAndGoodsId(String userId, String skuId);
}
