package com.lsh.vivo.controller;

import com.lsh.vivo.bean.request.user.UserSaveVO;
import com.lsh.vivo.bean.request.user.UserUpdateVO;
import com.lsh.vivo.bean.response.UserVO;
import com.lsh.vivo.bean.response.system.Result;
import com.lsh.vivo.entity.User;
import com.lsh.vivo.service.UserService;
import com.lsh.vivo.util.BeanCopyUtils;
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
    public Result<UserVO> register(@NonNull @Validated @RequestBody UserSaveVO user) {
        // 去除昵称前后空字符
        user.setNickname(user.getNickname().trim());
        User newUser = BeanCopyUtils.copyBean(user, User.class);
        userService.save(newUser);
        return Result.success();
    }

    /**
     * 更新用户信息
     *
     * @param update
     * @return
     */
    @PutMapping("/info")
    public Result<Boolean> updateUserInfo(@NotNull @RequestBody UserUpdateVO update) {
        User newUser = BeanCopyUtils.copyBean(update, User.class);
        Boolean result = userService.updateById(newUser);
        return Result.success(result);
    }
}
