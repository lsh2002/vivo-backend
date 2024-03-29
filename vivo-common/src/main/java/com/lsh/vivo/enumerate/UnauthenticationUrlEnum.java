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
     * 注册
     */
    URL_REGISTER("/register", "注册"),
    /**
     * 重置密码
     */
    URL_FORGET("/forget", "重置密码"),
    /**
     * 登录
     */
    URL_LOGIN("/login", "登录"),
    /**
     * 获取公钥
     */
    URL_PKEY("/public/pkey", "获取公钥"),

    /**
     * 获取公钥
     */
    URL_HOME("/", "主页"),

    URL_ERROR("/error", "异常");

    private final String url;

    private final String description;

    public static List<String> getUrls(){
        return Arrays.stream(values()).map(UnauthenticationUrlEnum::getUrl).collect(Collectors.toList());
    }
}
