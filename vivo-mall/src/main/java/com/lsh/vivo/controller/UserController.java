package com.lsh.vivo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.lsh.vivo.bean.request.user.UserSaveVO;
import com.lsh.vivo.bean.request.user.UserUpdateVO;
import com.lsh.vivo.bean.response.UserVO;
import com.lsh.vivo.bean.response.system.Result;
import com.lsh.vivo.entity.User;
import com.lsh.vivo.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author lsh
 * @since 2023-09-09 15:43
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * 用户注册
     *
     * @param user 用户注册请求
     */
    @PostMapping("/register")
    public void register(@NonNull @Validated @RequestBody UserSaveVO user) {
        // 去除昵称前后空字符
        user.setNickname(user.getNickname().trim());
        User newUser = BeanUtil.copyProperties(user, User.class);
        userService.save(newUser);
    }

    /**
     * 更新用户信息
     *
     * @param update
     * @return
     */
    @PutMapping("/info")
    public void updateUserInfo(@NotNull @RequestBody UserUpdateVO update) {
        User newUser = BeanUtil.copyProperties(update, User.class);
        Boolean result = userService.updateById(newUser);
    }
}
