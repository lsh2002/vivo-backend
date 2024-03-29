package com.lsh.vivo.bean.request.order;

import com.lsh.vivo.bean.request.system.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单
 *
 * @author lsh
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderSaveVO extends BaseRequest {

    /**
     * 用户id
     */
    private String userId;

    /**
     * sku id
     */
    private String skuId;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 收货人地址
     */
    private String receiverAddress;

    /**
     * 商品数量
     */
    private Integer num;

    /**
     * 商品单价
     */
    private Double price;

    /**
     * 付款时间
     */
    private Long payTime;
}