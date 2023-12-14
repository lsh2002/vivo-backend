package com.lsh.vivo.security.filter;

import com.lsh.vivo.bean.constant.GlobalConstant;
import com.lsh.vivo.util.IpUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 请求过滤器
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/24 16:24
 */
@Slf4j
@Component
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String ip = IpUtils.getIpAddr((HttpServletRequest) servletRequest);
        servletRequest.setAttribute(GlobalConstant.HTTP_REQUEST_IP, ip);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
