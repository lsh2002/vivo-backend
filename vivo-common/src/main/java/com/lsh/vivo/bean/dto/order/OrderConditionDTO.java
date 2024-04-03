package com.lsh.vivo.bean.dto.order;

import lombok.Data;

/**
 *
 * @author pjw
 * @version 1.0.0
 * @since 2023-11-15 15:00
 */
@Data
public class OrderConditionDTO {

    private String orderId;

    private String name;

    private String receiverName;

    private String receiverPhone;

    private String courierNumber;

    private String status;

    private String serviceTypeEnum;
}
