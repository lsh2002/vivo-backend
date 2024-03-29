package com.lsh.vivo.security.filter;

import com.google.gson.Gson;
import com.lsh.vivo.bean.constant.GlobalConstant;
import com.lsh.vivo.bean.response.system.Result;
import com.lsh.vivo.bean.security.UserDetail;
import com.lsh.vivo.entity.User;
import com.lsh.vivo.enumerate.BaseResultCodeEnum;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.enumerate.PermChangeStatusEnum;
import com.lsh.vivo.enumerate.UnauthenticationUrlEnum;
import com.lsh.vivo.service.UserService;
import com.lsh.vivo.util.JwtUtils;
import com.mybatisflex.core.query.QueryWrapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.lsh.vivo.entity.table.UserTableDef.USER;

/**
 * token验证类
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/23 17:06
 */
@Setter
@Slf4j
@Component
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws IOException, ServletException {
        if (SecurityContextHolder.getContext().getAuthentication() == null && !UnauthenticationUrlEnum.getUrls().contains(request.getServletPath())) {
            String authentication = request.getHeader(jwtUtils.getHeaderName());
            if (StringUtils.isNotBlank(authentication) && authentication.startsWith(jwtUtils.getTokenPrefix())) {
                String token = authentication.substring(jwtUtils.getTokenPrefix().length()).trim();
                String ip = (String) request.getAttribute(GlobalConstant.HTTP_REQUEST_IP);
                Claims claims = jwtUtils.extractAllClaims(token);
                if (claims != null) {
                    boolean valid = jwtUtils.isTokenValid(token, ip);
                    if (valid) {
                        String username = claims.get(GlobalConstant.HTTP_USER, String.class);
                        // 认证通过且token有效期只剩5min以内的，刷新token
                        Date expiration = claims.getExpiration();
                        // 将 LocalDateTime 对象转换为 LocalLocalDateTimeTime
                        LocalDateTime dateTime = expiration.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
                        Collection<? extends GrantedAuthority> authorityList = null;
                        String userId = null;
                        String userName = null;
                        // 检查是否比当前时间早5分钟以内或大于当前时间,是则返回新token
                        if (LocalDateTime.now().plusMinutes(jwtUtils.getRefreshTime()).isAfter(dateTime)) {
                            UserDetail userDetails = (UserDetail) userDetailsService.loadUserByUsername(username);
                            String newToken = jwtUtils.generateToken(userDetails, ip);
                            response.setHeader(jwtUtils.getHeaderName().toLowerCase(), newToken);
                            userId = userDetails.getId();
                            userName = userDetails.getUsername();
                            authorityList = userDetails.getAuthorities();
                        } else {
                            // 有效的token，判断用户是否还是I启用状态，并且权限没有发送变更
                            userId = jwtUtils.getUserId(claims);
                            QueryWrapper wrapper = userService.query()
                                    .select(USER.STATUS, USER.PERM_STATUS)
                                    .where(USER.ID.eq(userId));
                            User user = userService.getOne(wrapper);
                            // 是H状态，停用了，抛异常告知用户
                            if (CommonStatusEnum.H.name().equals(user.getStatus())) {
                                userRequestNowAllow(response, BaseResultCodeEnum.ERROR_ACCOUNT_HALT);
                                return;
                            }
                            // 权限发生变化了，抛异常告知用户
                            if (PermChangeStatusEnum.T.name().equals(user.getPermStatus())) {
                                userRequestNowAllow(response, BaseResultCodeEnum.ERROR_USER_BINDING_ROLE_PERM_CHANGE);
                                return;
                            }
                            List<String> authorities = jwtUtils.getAuthorities(claims);
                            userName = jwtUtils.getUserName(claims);
                            authorityList = AuthorityUtils.createAuthorityList(authorities);
                        }
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(
                                        username, null, authorityList);
                        request.setAttribute(GlobalConstant.HTTP_USER_ID, userId);
                        request.setAttribute(GlobalConstant.HTTP_USER, userName);
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContextHolderStrategy().getContext().setAuthentication(authToken);
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 用户被停用或者权限发生变更
     */
    protected void userRequestNowAllow(HttpServletResponse httpServletResponse, BaseResultCodeEnum resultCode) {
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Result<Void> result = Result.error(resultCode);
        try (PrintWriter printWriter = httpServletResponse.getWriter()) {
            printWriter.write(new Gson().toJson(result));
            printWriter.flush();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
