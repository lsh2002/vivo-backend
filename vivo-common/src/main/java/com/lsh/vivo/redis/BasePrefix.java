package com.lsh.vivo.redis;

/**
 * @program: SeckillProject
 * @description: 键值抽象类
 **/
public class BasePrefix implements KeyPrefix {
    private String prefix;

    public BasePrefix( String prefix) {
        this.prefix = prefix;
    }

    /**
     * 键生成
     * @return
     */
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className+":" + prefix;
    }
}
