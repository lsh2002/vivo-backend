package com.lsh.vivo.bean.request.order;

import com.lsh.vivo.bean.request.system.BaseRequest;
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
public class OrderStatusVO extends BaseRequest {

    private String orderId;

    private OrderStatusEnum status;

    private Long time;
}