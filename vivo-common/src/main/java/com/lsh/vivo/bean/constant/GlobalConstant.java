package com.lsh.vivo.bean.constant;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局参数
 *
 * @author ASUS
 */
@Slf4j
public class GlobalConstant {

    /**
     * 空值
     */
    public static final String STRING_EMPTY = "";

    /**
     * 缓存-验证码KEY值
     */
    public static final String CACHE_KEY_VERIFY_CODE = "VERIFY-CODE";

    /**
     * 请求来源IP
     */
    public static final String HTTP_REQUEST_IP = "REQUEST-IP";

    /**
     * 当前用户ID
     */
    public static final String HTTP_USER_ID = "USER-ID";

    /**
     * 当前用户名
     */
    public static final String HTTP_USER = "USER";

    /**
     * select(number(1))
     */
    public static final String SELECT_NUMBER = "1";
}
