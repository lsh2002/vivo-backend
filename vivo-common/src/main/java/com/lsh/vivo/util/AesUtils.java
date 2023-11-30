package com.lsh.vivo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * AESUtil类
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/23 9:30
 */
@Slf4j
@Component
public class AesUtils {

    /**
     * 密钥
     */
    @Value("${project.security.aes.key}")
    private String key;

    private static final String AES = "AES";

    /**
     * 初始向量IV, 初始向量IV的长度规定为128位16个字节, 初始向量的来源为随机生成.
     */
    @Value("${project.security.aes.vi}")
    private String vi;

    /**
     * 加密解密算法/加密模式/填充方式
     */
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    static {
        java.security.Security.setProperty("crypto.policy", "unlimited");
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance(AES);
        generator.init(256);
        SecretKey secretKey = generator.generateKey();
        byte[] encoded = secretKey.getEncoded();
        System.out.println(Base64.getEncoder().encodeToString(encoded));
    }

    /**
     * AES加密
     */
    public String encode(String content) {
        return encode(content, key, vi);
    }

    /**
     * AES加密
     */
    public String encode(String content, String key, String vi) {
        try {
            SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), AES);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(vi.getBytes()));

            // 获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byteEncode = content.getBytes(java.nio.charset.StandardCharsets.UTF_8);

            // 根据密码器的初始化方式加密
            byte[] encodeByte = cipher.doFinal(byteEncode);

            // 将加密后的数据转换为字符串
            return Base64.getEncoder().encodeToString(encodeByte);
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * AES解密
     */
    public String decode(String content) {
        try {
            SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), AES);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(vi.getBytes()));

            // 将加密并编码后的内容解码成字节数组
            byte[] decodeByte = Base64.getDecoder().decode(content);
            // 解密
            byte[] byteDecode = cipher.doFinal(decodeByte);
            return new String(byteDecode, java.nio.charset.StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }
}
