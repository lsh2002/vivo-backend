package com.lsh.vivo.controller;

import com.lsh.vivo.bean.request.AdminLoginVO;
import com.lsh.vivo.bean.response.system.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录模块 控制器
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/20 0:22
 */
@Slf4j
@RestController
public class AdminLoginController {

    /**
     * 登录
     */
    @PostMapping("/login")
    public void login(AdminLoginVO loginVO) {

    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    public void logout() {

    }
}
