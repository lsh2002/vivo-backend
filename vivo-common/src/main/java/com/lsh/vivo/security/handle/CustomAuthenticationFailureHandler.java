package com.lsh.vivo.security.handle;

import com.google.gson.Gson;
import com.lsh.vivo.bean.response.system.Result;
import com.lsh.vivo.enumerate.BaseResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * 登录失败
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 0:09
 */
@Slf4j
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse httpServletResponse, AuthenticationException exception) {
        log.error("url:{}", request.getRequestURL(), exception);
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Result<String> result = getResult(exception);

        try (PrintWriter printWriter = httpServletResponse.getWriter()) {
            printWriter.write(new Gson().toJson(result));
            printWriter.flush();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    private Result<String> getResult(AuthenticationException e) {
        //登录凭证（密码）异常
        if (e instanceof BadCredentialsException) {
            return Result.error(BaseResultCodeEnum.ERROR_USER_OR_PASSWORD, null);
        }
        //登陆凭证不够充分而抛出的异常
        if (e instanceof InsufficientAuthenticationException) {
            return Result.error(BaseResultCodeEnum.ERROR_NOT_LOGIN, null);
        }
        //用户名不存在异常
        if (e instanceof UsernameNotFoundException) {
            return Result.error(BaseResultCodeEnum.ERROR_USER_OR_PASSWORD, null);
        }
        //身份预认证失败异常
        if (e instanceof PreAuthenticatedCredentialsNotFoundException) {
            return Result.error(BaseResultCodeEnum.ERROR_USER_OR_PASSWORD, null);
        }
        //账户被禁用异常
        if (e instanceof DisabledException) {
            return Result.error(BaseResultCodeEnum.ERROR_ACCOUNT_HALT, null);
        }
        //登录凭证（密码）过期异常
        if (e instanceof CredentialsExpiredException) {
            return Result.error(BaseResultCodeEnum.ERROR_LOGIN_EXPIRED, null);
        }
        //账户过期异常
        if (e instanceof AccountExpiredException) {
            return Result.error(BaseResultCodeEnum.ERROR_LOGIN_EXPIRED, null);
        }
        //账户状态异常
        if (e instanceof AccountStatusException) {
            return Result.error(BaseResultCodeEnum.ERROR_LOGIN_EXPIRED, null);
        }
        //未配置AuthenticationProvider异常
        if (e instanceof ProviderNotFoundException) {
            return Result.error(BaseResultCodeEnum.ERROR_LOGIN_EXPIRED, null);
        }
        //由于系统问题而无法处理认证请求异常
        if (e instanceof AuthenticationServiceException) {
            return Result.error(BaseResultCodeEnum.ERROR_ACCOUNT, null);
        }
        return Result.error((String) null);
    }
}