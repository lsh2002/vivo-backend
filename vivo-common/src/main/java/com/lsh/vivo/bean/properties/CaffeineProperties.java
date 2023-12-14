package com.lsh.vivo.bean.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * CaffeineProperties类
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 11:24
 */
@Data
@Component
@ConfigurationProperties(prefix = "project.caffeine")
public class CaffeineProperties {

    /**
     * 过期时间
     */
    private int expireAfterAccess;
    /**
     * 单位
     */
    private TimeUnit unit;
    /**
     * 初始大小
     */
    private int initialCapacity;
    /**
     * 最大大小
     */
    private int maximumSize;

}
