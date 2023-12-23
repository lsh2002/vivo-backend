package com.lsh.vivo.controller;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.core.math.Calculator;
import com.lsh.vivo.bean.constant.GlobalConstant;
import com.lsh.vivo.bean.properties.RsaProperties;
import com.lsh.vivo.bean.request.CaptchaVO;
import com.lsh.vivo.enumerate.CaptchaGeneratorEnum;
import com.lsh.vivo.provider.ApplicationContextProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 公共模块控制器
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 0:22
 */
@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {

    private static String RSA_PUBLIC_KEY;

    private static AbstractCaptcha getCaptcha(CaptchaVO captchaDTO) {
        AbstractCaptcha captcha = switch (captchaDTO.getStyle()) {
            case GIF ->
                    CaptchaUtil.createGifCaptcha(captchaDTO.getWidth(), captchaDTO.getHeight(), captchaDTO.getCount());
            case SHEAR ->
                    CaptchaUtil.createShearCaptcha(captchaDTO.getWidth(), captchaDTO.getHeight(), captchaDTO.getCount(), 2);
            case CIRCLE ->
                    CaptchaUtil.createCircleCaptcha(captchaDTO.getWidth(), captchaDTO.getHeight(), captchaDTO.getCount(), 2);
            default ->
                    CaptchaUtil.createLineCaptcha(captchaDTO.getWidth(), captchaDTO.getHeight(), captchaDTO.getCount(), 2);
        };

        if (captchaDTO.getGenerator() == CaptchaGeneratorEnum.MATH) {
            // 自定义验证码内容为四则运算方式
            MathGenerator mathGenerator = new MathGenerator(captchaDTO.getCount());
            captcha.setGenerator(mathGenerator);
        }
        // 重新生成code
        captcha.createCode();
        return captcha;
    }

    @GetMapping("/verifyCode")
    public void getVerifyCode(CaptchaVO captchaDTO) {
        String ip = (String) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()).getAttribute(GlobalConstant.HTTP_REQUEST_IP, RequestAttributes.SCOPE_REQUEST);

        AbstractCaptcha captcha = getCaptcha(captchaDTO);
        String code = null;
        if (captchaDTO.getGenerator() == CaptchaGeneratorEnum.MATH) {
            code = String.valueOf(Calculator.conversion(captcha.getCode())).toLowerCase();
        } else {
            code = captcha.getCode().toLowerCase();
        }

        HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
        assert response != null;
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        response.setHeader("Pragma", "No-cache");
        try {
            // 输出到页面
            captcha.write(response.getOutputStream());
            // 关闭流
            response.getOutputStream().close();
        } catch (IOException e) {
            log.error(GlobalConstant.STRING_EMPTY, e);
            code = null;
        } finally {
            if (code != null) {
                CacheManager cacheManager = (CacheManager) ApplicationContextProvider.getBean("cacheManager");
                Cache cache = cacheManager.getCache(GlobalConstant.CACHE_KEY_VERIFY_CODE);
                if (cache != null && ip != null) {
                    cache.put(ip, code);
                }
            }
        }
    }

    @GetMapping("/pkey")
    public void getPublicKey() {
        HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
        assert response != null;
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        if (RSA_PUBLIC_KEY == null) {
            RsaProperties rsaProperties = ApplicationContextProvider.getBean(RsaProperties.class);
            RSA_PUBLIC_KEY = new String(Base64.encode(rsaProperties.getPublicKey().getEncoded()), StandardCharsets.UTF_8);
        }
        response.setHeader("pkey", RSA_PUBLIC_KEY);
    }

}
