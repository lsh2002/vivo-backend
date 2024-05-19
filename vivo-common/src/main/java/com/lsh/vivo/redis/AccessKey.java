package com.lsh.vivo.redis;

/**
 * @program: SeckillProject
 * @description: 限流工具键值
 **/
public class AccessKey extends BasePrefix {
    public AccessKey(String prefix) {
        super(prefix);
    }

    public static AccessKey withExpire() {
        return new AccessKey("access");
    }
}
