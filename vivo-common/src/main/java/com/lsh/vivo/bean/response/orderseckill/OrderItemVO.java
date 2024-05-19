package com.lsh.vivo.bean.response.orderseckill;

import com.lsh.vivo.bean.response.goods.GoodsSkuVO;
import com.lsh.vivo.bean.response.system.BaseUpdateVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单商品
 *
 * @author lsh
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderItemVO extends BaseUpdateVO {
    /**
     * 订单号
     */
    private String orderId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * sku id
     */
    private String skuId;

    /**
     * 商品数量
     */
    private Integer count;

    private Double totalPrice;

    /**
     * 商品sku
     */

    private GoodsSkuVO goodsSku;
}