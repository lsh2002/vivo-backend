package com.lsh.vivo.redis;

/**
 * @program: SeckillProject
 * @description: 用户对象键值生成基类
 **/
public class UserKey extends BasePrefix {
    //设置cookie过期时间
    public static final int TOKEN_EXPIRE = 3600*24 * 2;

    private UserKey(String prefix) {
        super(prefix);
    }
    public static UserKey token = new UserKey("tk");

}
