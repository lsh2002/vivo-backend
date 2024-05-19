package com.lsh.vivo.bean.request.order;

import com.lsh.vivo.bean.request.orderItem.OrderItemSaveVO;
import com.lsh.vivo.bean.request.system.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

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
     * 商品单价
     */
    private Double totalPrice;

    /**
     * 下单时间
     */
    private Long orderTime;

    private String requestNo;

    /**
     * 秒杀id
     */
    private String seckillId;

    private List<OrderItemSaveVO> orderItems;

    private boolean cart;
}