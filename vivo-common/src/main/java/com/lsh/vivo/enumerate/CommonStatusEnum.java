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
public enum CommonStatusEnum implements CommonFormEnumParser<CommonStatusEnum> {
    /**
     * 有效
     */
    I("有效"),

    /**
     * 锁定
     */
    S("锁定"),

    /**
     * 无效
     */
    H("无效");

    /**
     * 描述
     */
    private final String description;

    @Override
    public String toString() {
        return this.name() + ":" + this.description;
    }

    @Override
    public CommonStatusEnum fromValue(String input) {
        for (CommonStatusEnum statusEnum : values()) {
            if (Objects.equals(statusEnum.name(), input)) {
                return statusEnum;
            }
        }
        return null;
    }
}