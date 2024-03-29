package com.lsh.vivo.bean.response.cart;

import com.lsh.vivo.bean.response.goods.GoodsSkuVO;
import com.lsh.vivo.bean.response.system.BaseEntityVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 购物车
 *
 * @author ASUS
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CartVO extends BaseEntityVO {

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

    private GoodsSkuVO goodsSku;
}