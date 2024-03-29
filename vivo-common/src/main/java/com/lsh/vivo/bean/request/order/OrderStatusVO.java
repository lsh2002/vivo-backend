package com.lsh.vivo.bean.request.order;

import com.lsh.vivo.bean.request.system.BaseRequest;
import com.lsh.vivo.enumerate.OrderStatusEnum;
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
public class OrderStatusVO extends BaseRequest {

    private List<String> orderIds;

    private OrderStatusEnum status;

    private Long time;
}