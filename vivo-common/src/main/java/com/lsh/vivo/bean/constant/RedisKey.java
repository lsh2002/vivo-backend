package com.lsh.vivo.bean.constant;

/**
 * redis键
 *
 * @author lsh
 * @since 2023-09-09 15:43
 */
public class RedisKey {
    public static final String SECKILL_PRODUCT_LIST = "seckill:product:list:";
    public static final String SECKILL_PRODUCT = "seckill:product:id:";
    public static final String SECKILL_PRODUCT_STOCK = "seckill:product:stock:id:";
    public static final String SECKILL_PRODUCT_USER_LIST = "seckill:product:user:list";
    public static final String SECKILL_RABBITMQ_ID = "seckill:rabbitmq:id";

    /**
     * 商城登录用户
     */
    public static final String STORE_LOGIN_USER = "storeLogin:";

    /**
     * 后台管理系统登录用户
     */
    public static final String ADMIN_LOGIN_USER = "adminLogin:";

    /**
     * 商城登录用户过期时间（秒）
     */
    public static final Integer STORE_LOGIN_TIME_OUT = 24 * 60 * 60 * 1000;

    /**
     * 后台管理系统登录用户过期时间（秒）
     */
    public static final Integer ADMIN_LOGIN_TIME_OUT = 60 * 60 * 1000;
}
