package com.lsh.vivo.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Jwt配置
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/23 18:44
 */
@Data
@Component
@ConfigurationProperties(prefix = "project.jwt")
public class JwtProperties {

    private String secret;
    /**
     * 主题
     */
    private String subject;
    /**
     * 时效性
     */
    private Long expireTime;
    /**
     * 返回header的name
     */
    private String headerName;
    /**
     * token接收格式前缀
     */
    private String tokenPrefix;

    /**
     * token刷新剩余时间
     */
    private Integer refreshExpireTime;
}
