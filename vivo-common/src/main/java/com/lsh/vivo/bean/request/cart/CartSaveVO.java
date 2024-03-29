package com.lsh.vivo.bean.request.cart;

import com.lsh.vivo.bean.request.system.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 购物车
 *
 * @author ASUS
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CartSaveVO extends BaseRequest {

    /**
     * 用户id
     */
    private String userId;

    /**
     * skuId
     */
    private String skuId;

    /**
     * 商品数量
     */
    private Integer num;
}