package com.lsh.vivo.bean.response.order;

import com.lsh.vivo.bean.response.goods.GoodsSkuVO;
import com.lsh.vivo.bean.response.system.BaseEntityVO;
import com.lsh.vivo.enumerate.OrderStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单
 *
 * @author lsh
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderVO extends BaseEntityVO {
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

    /**
     * 发货时间
     */
    private Long deliverTime;

    /**
     * 完成时间
     */
    private Long finishTime;

    /**
     * 取消时间
     */
    private Long cancelTime;

    /**
     * 快递单号
     */
    private String courierNumber;

    private GoodsSkuVO goodsSku;

    private OrderStatusEnum status;
}