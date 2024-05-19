package com.lsh.vivo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfiguration {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        log.info("开始创建redis模板对象");

        RedisTemplate redisTemplate = new RedisTemplate();

        //设置redis连接工厂对象
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        //设置 redis key 的序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        //设置 redis 值的序列化器
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(Object.class));

        return redisTemplate;
    }
}