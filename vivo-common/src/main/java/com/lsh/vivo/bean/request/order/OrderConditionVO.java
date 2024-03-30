package com.lsh.vivo.bean.request.order;

import com.lsh.vivo.bean.request.system.PageRequest;
import com.lsh.vivo.enumerate.OrderStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author pjw
 * @version 1.0.0
 * @since 2023-11-15 15:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderConditionVO extends PageRequest {

    private String orderId;

    private String name;

    private String receiverName;

    private String receiverPhone;

    private String courierNumber;

    private OrderStatusEnum status;
}
