package com.lsh.vivo.security.handle;

import com.google.gson.Gson;
import com.lsh.vivo.bean.response.system.Result;
import com.lsh.vivo.enumerate.BaseResultCodeEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * 多设备登录
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 0:17
 */
@Slf4j
@Component
public class CustomSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = event.getResponse();
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Result<Void> result = Result.error(BaseResultCodeEnum.ERROR_SECOND_DEVICE_ONLINE);
        try (PrintWriter printWriter = httpServletResponse.getWriter()) {
            printWriter.write(new Gson().toJson(result));
            printWriter.flush();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
