package com.lsh.vivo.security.filter;

import com.google.gson.Gson;
import com.lsh.vivo.bean.constant.GlobalConstant;
import com.lsh.vivo.bean.response.system.Result;
import com.lsh.vivo.enumerate.BaseResultCodeEnum;
import com.lsh.vivo.provider.ApplicationContextProvider;
import com.lsh.vivo.util.IpUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * 验证码统一类
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 23:41
 */
@Order(2)
@Slf4j
@Component
public class VerifyCaptchaFilter implements Filter {

    private static final List<String> CAPTCHA_URL = List.of("/login");

    private static final String CAPTCHA_PARAM_NAME = "verifyCode";

    protected void verifyError(HttpServletResponse httpServletResponse){
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Result<Void> result = Result.error(BaseResultCodeEnum.ERROR_VERIFY_TIMEOUT);
        try (PrintWriter printWriter = httpServletResponse.getWriter()) {
            printWriter.write(new Gson().toJson(result));
            printWriter.flush();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        if (!CAPTCHA_URL.contains(request.getServletPath())){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String captcha = request.getParameter(CAPTCHA_PARAM_NAME);

        if (StringUtils.isNotBlank(captcha)){
            CacheManager cacheManager = (CacheManager) ApplicationContextProvider.getBean("cacheManager");
            Cache cache = cacheManager.getCache(GlobalConstant.CACHE_KEY_VERIFY_CODE);
            String ip = IpUtils.getIpAddr(request);
            assert cache != null;
            String inputCaptcha = cache.get(ip, String.class);
            if (!Objects.equals(inputCaptcha, captcha.toLowerCase())){
                captcha = null;
            }
            cache.evict(ip);
        }
        if (StringUtils.isBlank(captcha)){
            verifyError((HttpServletResponse)servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}