package com.lsh.vivo.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.lsh.vivo.bean.properties.CaffeineProperties;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cache配置
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/24 18:45
 */
@Slf4j
@Configuration
public class CaffeineCacheConfiguration {

    @Resource
    private CaffeineProperties caffeineProperties;

    /**
     * 配置缓存管理器
     *
     * @return 缓存管理器
     */
    @Bean("cacheManager")
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                // 设置最后一次写入或访问后经过固定时间过期
                .expireAfterAccess(caffeineProperties.getExpireAfterAccess(), caffeineProperties.getUnit())
                // 初始的缓存空间大小
                .initialCapacity(caffeineProperties.getInitialCapacity())
                // 缓存的最大条数
                .maximumSize(caffeineProperties.getMaximumSize()));
        return cacheManager;
    }

    /**
     * 配置缓存管理器
     *
     * @return 缓存管理器
     */
    @Bean("persistentCacheManager")
    public CacheManager backListCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                // 初始的缓存空间大小
                .initialCapacity(caffeineProperties.getInitialCapacity()));
        return cacheManager;
    }
}
