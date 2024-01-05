package com.lsh.vivo.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 商品状态
 *
 * @author lsh
 * @version 1.0.0
 * @since 2023-09-14 16:29
 */
@Slf4j
@AllArgsConstructor
@Getter
public enum GoodsStatusEnum implements CommonFormEnumParser<GoodsStatusEnum> {
    /**
     * 上架
     */
    U("上架"),

    /**
     * 锁定
     */
    D("下架");

    /**
     * 描述
     */
    private final String description;

    @Override
    public String toString() {
        return this.name() + ":" + this.description;
    }

    @Override
    public GoodsStatusEnum fromValue(String input) {
        for (GoodsStatusEnum statusEnum : values()) {
            if (Objects.equals(statusEnum.name(), input)) {
                return statusEnum;
            }
        }
        return null;
    }
}