package com.lsh.vivo.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 验证码生成类型
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 22:05
 */
@Slf4j
@AllArgsConstructor
@Getter
public enum CaptchaGeneratorEnum implements CommonFormEnumParser<CaptchaGeneratorEnum> {

    /**
     * 默认
     */
    DEFAULT("固定验证码"),
    /**
     * 算数
     */
    MATH("算术验证码");

    final String description;

    @Override
    public String toString() {
        return this.name() + ":" + this.description;
    }

    @Override
    public CaptchaGeneratorEnum fromValue(String input) {
        for (CaptchaGeneratorEnum generatorEnum : values()) {
            if (Objects.equals(generatorEnum.name(), input)) {
                return generatorEnum;
            }
        }
        throw new IllegalArgumentException("Invalid Generator value: " + input);
    }
}