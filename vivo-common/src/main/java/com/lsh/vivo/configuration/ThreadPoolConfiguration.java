package com.lsh.vivo.configuration;

import com.lsh.vivo.bean.properties.ThreadPoolProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ThreadPool配置
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/24 18:45
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class ThreadPoolConfiguration {

    private final ThreadPoolProperties threadPoolProperties;

    @Bean(name = "threadPool")
    public ThreadPoolTaskExecutor threadPool() {
        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        //核心数，一直都能工作的数量
        threadPoolExecutor.setCorePoolSize(threadPoolProperties.getCorePoolSize());
        //请求处理大时，可以开放的最大工作数
        threadPoolExecutor.setMaxPoolSize(threadPoolProperties.getMaximumPoolSize());
        //开启最大工作数后，当无请求时，还让其存活的时间
        threadPoolExecutor.setKeepAliveSeconds(threadPoolProperties.getKeepAliveTime());
        threadPoolExecutor.setTaskDecorator(new ThreadTaskDecorator());
        threadPoolExecutor.setThreadFactory(Executors.defaultThreadFactory());
        threadPoolExecutor.setThreadNamePrefix(threadPoolProperties.getThreadPrefixName());
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return threadPoolExecutor;
    }
}
