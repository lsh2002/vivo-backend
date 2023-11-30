package com.lsh.vivo.util;

import com.lsh.vivo.bean.properties.RsaProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSAUtils类
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/22 21:49
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RSAUtils {

    private static final String ALGORITHM_RSA = "RSA";
    private final RsaProperties rsaProperties;

    public static void main(String[] args) throws Exception {
        RSAUtils rsaUtils = new RSAUtils(null);
        String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC0hR8eKM+8YN0O609srsG8t0KS1vPh/LXBY6s8MQ+R4evtTwg64+WAT3KnwUpVG1tthGY5Uc7ldF9Tf5zTHRiRhxHczxV6juww+d9Ffp5cxy+inpDw+4pIk/eqQCMkOqHRRIWcP7Lc9WQr80+CxOgVE3B3b6iwGx5J8CSoxOH2AwIDAQAB";
        String priKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALSFHx4oz7xg3Q7rT2yuwby3QpLW8+H8tcFjqzwxD5Hh6+1PCDrj5YBPcqfBSlUbW22EZjlRzuV0X1N/nNMdGJGHEdzPFXqO7DD530V+nlzHL6KekPD7ikiT96pAIyQ6odFEhZw/stz1ZCvzT4LE6BUTcHdvqLAbHknwJKjE4fYDAgMBAAECgYAArFxu++4M0eq70fHbuXsAxRYTpWUgvDcfmDpngOasiC8U+7zpSXOUUEzgvcOmLSVPt8Hux+4qDR9/GJiVgnUr9giePI50yb9cloTSZcUGpGlW0d40LuS59pzJnR2uwW6lFde7x3cj9LFmq+nAH+1qFzZsNWS6lq54ipUPx0jD5QJBAMA681sABjzFcfwuenPa+3xIf/WJNUlylltb9E1254iQSRav5Iw7qV6cVJ+zSIWhvPIWFsRw8aBKR6AyaSyY7uUCQQDwZ67Lk9qs1QSWn71Uv86GGJIxQwcbBiV9idgNUD4Kouqj4g2VG5LcMXur12V9X/Mj7CR3RDDn92tfaJoR+RrHAkBkoXer98NMJQCax6oK5GQql7mEzCM9I+NaSDUIKGrkrsPUQJggYUs04Bnc0HnymszS3oVkqMuU4c3FKx6r3hORAkEA23lcu+Bc2EKVMMneuJtveT6/YJfXydUfru+EPgtOyLkZS8qZKdKFjd7jD+vSbNaHC/mPBQ9NMsjPzseZOOVZiwJAEJk2djFSU3HdfycUt5tViwYLyEIaVuMtn4L2jyI95YqVtI0bq+H5JETZ6+MO0TimZ4MrkFuR1OxXbyK/1GT60Q==";
        String content = "Admin";
//        String encry = rsaUtils.encryption(content, pubKey);
//        String decry = rsaUtils.decryption(encry, priKey);
//        System.out.println("content:" + content);
//        System.out.println("encry:" + encry);
//        System.out.println("decry:" + decry);

    }

    public String encryption(String content) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return encryption(content, rsaProperties.getPublicKey());
    }

    public String encryption(String content, String key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
        byte[] keyBytes = Base64.decodeBase64(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64.encodeBase64String(cipher.doFinal(content.getBytes()));
    }

    public String encryption(String content, RSAPublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64.encodeBase64String(cipher.doFinal(content.getBytes()));
    }

    public String decryption(String content) throws Exception {
        return decryption(content, rsaProperties.getPrivateKey());
    }

    public String decryption(String content, RSAPrivateKey privateKey) throws Exception {
        byte[] contentByte = Base64.decodeBase64(content.getBytes(StandardCharsets.UTF_8));
        Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(contentByte));
    }

    /**
     * 随机生成密钥对
     *
     * @throws NoSuchAlgorithmException
     */
    public void generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_RSA);
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //私钥
        PrivateKey privateK = keyPair.getPrivate();
        //公钥
        PublicKey publicK = keyPair.getPublic();

        //PrivateKey2String
        String privateKey = new String(Base64.encodeBase64(privateK.getEncoded()));
        //PublicKey2String
        String publicKey = new String(Base64.encodeBase64(publicK.getEncoded()));
        System.out.println("公钥：" + publicKey);
        System.out.println("私钥：" + privateKey);

    }
}
