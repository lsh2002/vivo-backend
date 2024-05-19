package com.lsh.vivo.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
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
public enum ServiceTypeEnum implements CommonFormEnumParser<ServiceTypeEnum> {

    S("成功"),

    C("已取消"),

    R("商家拒绝"),

    D("售后中"),
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
    public ServiceTypeEnum fromValue(String input) {
        for (ServiceTypeEnum statusEnum : values()) {
            if (Objects.equals(statusEnum.name(), input)) {
                return statusEnum;
            }
        }
        return null;
    }

    public static List<String> listServiceType() {
        List<String> list = new ArrayList<>();
        for (ServiceTypeEnum value : values()) {
            list.add(value.name());
        }
        return list;
    }
}