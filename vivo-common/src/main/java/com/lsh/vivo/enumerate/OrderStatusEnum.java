package com.lsh.vivo.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 订单状态
 *
 * @author lsh
 * @version 1.0.0
 * @since 2023-09-14 16:29
 */
@Slf4j
@AllArgsConstructor
@Getter
public enum OrderStatusEnum implements CommonFormEnumParser<OrderStatusEnum> {

    A("全部"),

    P("待付款"),

    S("待发货"),

    R("待收货"),

    F("已完成"),

    C("已取消"),

    T("删除"),
    ;

    /**
     * 描述
     */
    private final String description;

    @Override
    public String toString() {
        return this.name() + ":" + this.description;
    }

    @Override
    public OrderStatusEnum fromValue(String input) {
        for (OrderStatusEnum statusEnum : values()) {
            if (Objects.equals(statusEnum.name(), input)) {
                return statusEnum;
            }
        }
        return null;
    }
}