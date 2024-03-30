package com.lsh.vivo.bean.request.order;

import com.lsh.vivo.bean.response.system.BaseUpdateVO;
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
public class OrderUpdateVO extends BaseUpdateVO {

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

    private OrderStatusEnum status;

    private String courierNumber;

    private Long deliverTime;
}