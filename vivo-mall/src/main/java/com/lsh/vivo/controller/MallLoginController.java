package com.lsh.vivo.controller;

import com.lsh.vivo.bean.request.user.UserLoginVO;
import com.lsh.vivo.bean.response.MallUserLoginVO;
import com.lsh.vivo.bean.response.system.Result;
import com.lsh.vivo.service.MallLoginUserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    private final MallLoginUserService mallLoginUserService;

    /**
     * 用户登录
     *
     * @param user 用户登录请求
     */
    @PostMapping("/login")
    public Result<MallUserLoginVO> login(@NotNull @Validated @RequestBody UserLoginVO user) {
        MallUserLoginVO mallUserLoginVO = mallLoginUserService.login(user.getUsername(), user.getPassword());
        return Result.success(mallUserLoginVO);
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public Result<Boolean> userLogout() {
        boolean result = mallLoginUserService.logout();
        return Result.success(result);
    }
}
