package com.lsh.vivo.controller;

import com.lsh.vivo.bean.request.user.UserLoginVO;
import com.lsh.vivo.bean.request.user.UserRegisterVO;
import com.lsh.vivo.entity.User;
import com.lsh.vivo.enumerate.BaseResultCodeEnum;
import com.lsh.vivo.exception.BaseRequestErrorException;
import com.lsh.vivo.mapper.struct.UserMpp;
import com.lsh.vivo.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
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

    private final UserService userService;

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

    /**
     * 用户注册
     *
     * @param user 用户登录请求
     */
    @PostMapping("/register")
    public void register(@RequestBody @NotNull UserRegisterVO user) {
        if (!user.getPassword().equals(user.getCheckPass())) {
            throw new BaseRequestErrorException(BaseResultCodeEnum.ERROR_REGISTER_CHECKPASS);
        }
        User newUser = UserMpp.INSTANCE.toUser(user);
        userService.save(newUser);
    }
}
