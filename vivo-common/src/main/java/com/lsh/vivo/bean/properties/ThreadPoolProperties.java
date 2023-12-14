package com.lsh.vivo.bean.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolProperties类
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 11:24
 */
@Data
@Component
@ConfigurationProperties(prefix = "project.thread-pool")
public class ThreadPoolProperties {

    /**
     * 核心线程数
     */
    private int corePoolSize;
    /**
     * 最大线程数
     */
    private int maximumPoolSize;
    /**
     * 活跃时长
     */
    private int keepAliveTime;
    /**
     * 活跃时长单位
     */
    private TimeUnit unit;
    /**
     * 队列数
     */
    private int workQueueSize;
    /**
     * 线程前缀名
     */
    private String threadPrefixName;
}
