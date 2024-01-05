package com.lsh.vivo.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 商品类目级别枚举类
 *
 * @author pjw
 * @version 1.0.0
 * @since 2023-10-03 21:23
 */
@Slf4j
@AllArgsConstructor
@Getter
public enum GoodsCatLevelEnum implements CommonFormEnumParser<GoodsCatLevelEnum> {
    /**
     * 一级类别
     */
    F("一级类别"),
    /**
     * 二级类别
     */
    S("二级类别");

    /**
     * 描述
     */
    private final String description;

    @Override
    public String toString() {
        return this.name() + ":" + this.description;
    }

    @Override
    public GoodsCatLevelEnum fromValue(String input) {
        for (GoodsCatLevelEnum levelEnum : values()) {
            if (Objects.equals(levelEnum.name(), input)) {
                return levelEnum;
            }
        }
        return null;
    }
}
