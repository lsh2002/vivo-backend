package com.lsh.vivo.interceptor;

import com.lsh.vivo.bean.constant.GlobalConstant;
import com.lsh.vivo.util.OauthContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

/**
 * 拦截器-OauthContext缓存和清空处理
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 11:21
 */
@Slf4j
public class OauthContextInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        //防止结束清空异常，进入之前先做一次清楚，避免出现异常
        OauthContext.clear();
        String userId = StringUtils.defaultString((String) request.getAttribute(GlobalConstant.HTTP_USER_ID), "SYSTEM");
        OauthContext.set(GlobalConstant.HTTP_USER_ID, userId);
        String user = StringUtils.defaultString((String) request.getAttribute(GlobalConstant.HTTP_USER), "SYSTEM");
        OauthContext.set(GlobalConstant.HTTP_USER, user);
        String ip = (String) request.getAttribute(GlobalConstant.HTTP_REQUEST_IP);
        OauthContext.set(GlobalConstant.HTTP_REQUEST_IP, ip);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空ThreadLocal缓存
        OauthContext.clear();
    }
}
