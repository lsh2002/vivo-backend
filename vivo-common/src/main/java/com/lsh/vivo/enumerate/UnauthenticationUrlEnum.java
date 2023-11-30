package com.lsh.vivo.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UnauthenticationUrlEnum类
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/28 0:22
 */
@Slf4j
@Getter
@AllArgsConstructor
public enum UnauthenticationUrlEnum {
    /**
     * 验证码
     */
    URL_VERIFY_CODE("/public/verifyCode", "验证码"),
    /**
     * 登录
     */
    URL_LOGIN("/login", "登录"),
    /**
     * 获取公钥
     */
    URL_PKEY("/public/pkey", "获取公钥"),

    URL_ERROR("/error", "异常");

    private final String url;

    private final String description;

    public static List<String> getUrls(){
        return Arrays.stream(values()).map(UnauthenticationUrlEnum::getUrl).collect(Collectors.toList());
    }
}
