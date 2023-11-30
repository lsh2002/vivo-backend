package com.lsh.vivo.util;

import com.lsh.vivo.bean.security.UserDetail;
import com.lsh.vivo.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Security工具类
 *
 * @author lsh
 * @since 2023-08-02 10:39
 */
public class SecurityUtils {

    /**
     * 获取用户
     **/
    public static UserDetail getLoginUser() {
        try {
            return (UserDetail) getAuthentication().getPrincipal();
        } catch (Exception e) {
            return new UserDetail();
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getUserId() {
        return getLoginUser().getId();
    }

    public static String getUsername() {
        return getLoginUser().getUsername();
    }

    public static String getNickname() {
        return getLoginUser().getNickname();
    }
}