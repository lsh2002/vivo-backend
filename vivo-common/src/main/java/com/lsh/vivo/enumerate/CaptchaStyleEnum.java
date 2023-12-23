package com.lsh.vivo.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * Captcha 干扰线样式
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 0:36
 */
@AllArgsConstructor
@Getter
public enum CaptchaStyleEnum implements CommonFormEnumParser<CaptchaStyleEnum> {

    /**
     * 线型干扰线
     */
    LINE("线型干扰线"),
    /**
     * 圆圈干扰线
     */
    CIRCLE("圆圈干扰线"),
    /**
     * 扭曲干扰线
     */
    SHEAR("扭曲干扰线"),
    /**
     * GIF动图
     */
    GIF("GIF动图");

    final String description;


    @Override
    public String toString() {
        return this.name() + ":" + this.description;
    }

    @Override
    public CaptchaStyleEnum fromValue(String input) {
        for (CaptchaStyleEnum styleEnum : values()) {
            if (Objects.equals(styleEnum.name(), input)) {
                return styleEnum;
            }
        }
        throw new IllegalArgumentException("Invalid Generator value: " + input);
    }
}