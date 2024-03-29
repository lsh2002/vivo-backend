package com.lsh.vivo.security.handle;

import com.google.gson.Gson;
import com.lsh.vivo.bean.response.system.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * 登出
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 0:09
 */
@Slf4j
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        request.getSession().removeAttribute("token");
        Result<Void> result = Result.success();
        try (PrintWriter printWriter = httpServletResponse.getWriter()) {
            printWriter.write(new Gson().toJson(result));
            printWriter.flush();
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
    }
}
