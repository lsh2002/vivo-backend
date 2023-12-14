package com.lsh.vivo.security.handle;

import com.google.gson.Gson;
import com.lsh.vivo.bean.constant.GlobalConstant;
import com.lsh.vivo.bean.response.system.Result;
import com.lsh.vivo.bean.security.UserDetail;
import com.lsh.vivo.util.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


/**
 * 登录成功
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 0:09
 */
@Slf4j
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtUtils jwtUtils;

    @Value("${project.jwt.header-name}")
    private String headerName;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse httpServletResponse, Authentication authentication) {
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        if (StringUtils.isNotBlank(headerName)) {
            UserDetail userDetail = (UserDetail) (authentication.getPrincipal());
            String ip = (String) request.getAttribute(GlobalConstant.HTTP_REQUEST_IP);
            String token = jwtUtils.generateToken(userDetail, ip);
            httpServletResponse.setHeader(headerName.toLowerCase(), token);
        }
        Result<Void> result = Result.success();

        try (PrintWriter printWriter = httpServletResponse.getWriter()) {
            printWriter.write(new Gson().toJson(result));
            printWriter.flush();
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
    }
}
