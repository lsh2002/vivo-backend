package com.lsh.vivo.bean.request.orderItem;

import lombok.Data;

/**
 * 订单商品
 *
 * @author lsh
 */
@Data
public class OrderItemSaveVO {

    /**
     * sku id
     */
    private String skuId;

    /**
     * 商品数量
     */
    private Integer count;

    private Double totalPrice;
}