package com.lsh.vivo.bean.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * RSA 算法属性
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/11/4 11:04
 */
@Data
@Component
@ConfigurationProperties(prefix = "project.security.rsa")
public class RsaProperties {

    /**
     * 公钥
     */
    private RSAPublicKey publicKey;
    /**
     * 私钥
     */
    private RSAPrivateKey privateKey;

}
