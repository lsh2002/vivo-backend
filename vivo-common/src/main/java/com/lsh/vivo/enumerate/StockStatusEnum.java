package com.lsh.vivo.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 通用状态
 *
 * @author lsh
 * @version 1.0.0
 * @since 2023-09-14 16:29
 */
@Slf4j
@AllArgsConstructor
@Getter
public enum StockStatusEnum implements CommonFormEnumParser<StockStatusEnum> {
    /**
     * 入库
     */
    I("入库"),

    /**
     * 出库
     */
    O("出库"),;

    /**
     * 描述
     */
    private final String description;

    @Override
    public String toString() {
        return this.name() + ":" + this.description;
    }

    @Override
    public StockStatusEnum fromValue(String input) {
        for (StockStatusEnum statusEnum : values()) {
            if (Objects.equals(statusEnum.name(), input)) {
                return statusEnum;
            }
        }
        return null;
    }
}