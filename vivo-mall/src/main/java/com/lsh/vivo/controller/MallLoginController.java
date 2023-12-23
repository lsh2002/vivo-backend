package com.lsh.vivo.controller;

import com.lsh.vivo.bean.request.user.UserLoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lsh
 * @since 2023-09-09 15:43
 */
@RestController
@RequestMapping
@RequiredArgsConstructor
public class MallLoginController {

    /**
     * 用户登录
     *
     * @param user 用户登录请求
     */
    @PostMapping("/login")
    public void login(UserLoginVO user) {

    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public void userLogout() {

    }
}
