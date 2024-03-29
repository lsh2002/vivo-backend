package com.lsh.vivo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.lsh.vivo.bean.request.user.UserPassVO;
import com.lsh.vivo.bean.request.user.UserUpdateVO;
import com.lsh.vivo.bean.response.user.UserBasicInfoVO;
import com.lsh.vivo.entity.User;
import com.lsh.vivo.mapper.struct.UserMpp;
import com.lsh.vivo.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
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

    /**
     * 获取用户信息
     */
    @GetMapping("/info")
    public UserBasicInfoVO getUserInfo() {
        return getCommonBasicInfo();
    }

    /**
     * 统一获取用户基本信息
     */
    private UserBasicInfoVO getCommonBasicInfo() {
        User user = userService.getBasicInfo();
        return UserMpp.INSTANCE.toInfoVO(user, null);
    }

    @PostMapping("/pass")
    private boolean updatePass(@RequestBody @NotNull UserPassVO userPassVO) {
        return userService.updatePass(userPassVO.getUserId(), userPassVO.getOldPass(), userPassVO.getNewPass(), userPassVO.getCheckPass());
    }
}
