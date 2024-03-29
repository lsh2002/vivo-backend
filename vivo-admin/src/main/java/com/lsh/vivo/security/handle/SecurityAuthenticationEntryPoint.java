package com.lsh.vivo.security.handle;


import com.google.gson.Gson;
import com.lsh.vivo.bean.response.system.Result;
import com.lsh.vivo.enumerate.BaseResultCodeEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * Security请先登录类
 *
 * @author AdolphLv
 * @version 1.0
 * 2023/9/21 22:06
 */
@Slf4j
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.error("url:{}", httpServletRequest.getRequestURL(), e);
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Result<Void> result = getResult(e);
        try (PrintWriter printWriter = httpServletResponse.getWriter()) {
            printWriter.write(new Gson().toJson(result));
            printWriter.flush();
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
    }

    private Result<Void> getResult(AuthenticationException e) {
        //登录凭证（密码）异常
        if (e instanceof BadCredentialsException) {
            return Result.error(BaseResultCodeEnum.ERROR_USER_OR_PASSWORD);
        }
        //登陆凭证不够充分而抛出的异常
        if (e instanceof InsufficientAuthenticationException) {
            return Result.error(BaseResultCodeEnum.ERROR_NOR_PERMISSION);
        }
        //用户名不存在异常
        if (e instanceof UsernameNotFoundException) {
            return Result.error(BaseResultCodeEnum.ERROR_USER_OR_PASSWORD);
        }
        //身份预认证失败异常
        if (e instanceof PreAuthenticatedCredentialsNotFoundException) {
            return Result.error(BaseResultCodeEnum.ERROR_USER_OR_PASSWORD);
        }
        //账户被禁用异常
        if (e instanceof DisabledException) {
            return Result.error(BaseResultCodeEnum.ERROR_ACCOUNT);
        }
        //登录凭证（密码）过期异常
        if (e instanceof CredentialsExpiredException) {
            return Result.error(BaseResultCodeEnum.ERROR_LOGIN_EXPIRED);
        }
        //账户过期异常
        if (e instanceof AccountExpiredException) {
            return Result.error(BaseResultCodeEnum.ERROR_LOGIN_EXPIRED);
        }
        //账户被锁定异常
        if (e instanceof LockedException) {
            return Result.error(BaseResultCodeEnum.ERROR_ACCOUNT);
        }
        //账户状态异常
        if (e instanceof AccountStatusException) {
            return Result.error(BaseResultCodeEnum.ERROR_LOGIN_EXPIRED);
        }
        //未配置AuthenticationProvider异常
        if (e instanceof ProviderNotFoundException) {
            return Result.error(BaseResultCodeEnum.ERROR_LOGIN_EXPIRED);
        }
        if (e instanceof AuthenticationCredentialsNotFoundException) {
            return Result.error(BaseResultCodeEnum.ERROR_LOGIN_EXPIRED);
        }
        //由于系统问题而无法处理认证请求异常
        if (e instanceof AuthenticationServiceException) {
            return Result.error(BaseResultCodeEnum.ERROR_ACCOUNT);
        }
        return Result.error();
    }
}
