package com.lsh.vivo.bean.response.order;

import com.lsh.vivo.bean.response.orderseckill.OrderItemVO;
import com.lsh.vivo.bean.response.system.BaseUpdateVO;
import com.lsh.vivo.enumerate.OrderStatusEnum;
import com.lsh.vivo.enumerate.ServiceTypeEnum;
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
public class OrderVO extends BaseUpdateVO {
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
    private ServiceTypeEnum serviceType;

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

    private Long serviceTime;

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

    private OrderStatusEnum status;

    private List<OrderItemVO> orderItems;

    private String seckillId;
}