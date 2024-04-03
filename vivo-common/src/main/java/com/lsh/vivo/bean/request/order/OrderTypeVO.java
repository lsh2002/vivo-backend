package com.lsh.vivo.bean.request.order;

import com.lsh.vivo.bean.response.system.BaseUpdateVO;
import com.lsh.vivo.enumerate.OrderStatusEnum;
import com.lsh.vivo.enumerate.ServiceTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单
 *
 * @author lsh
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderTypeVO extends BaseUpdateVO {

    private ServiceTypeEnum serviceType;

    private OrderStatusEnum status;

    private Long serviceTime;
}