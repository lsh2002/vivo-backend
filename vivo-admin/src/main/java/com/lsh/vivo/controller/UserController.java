package com.lsh.vivo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.lsh.vivo.bean.dto.user.UserConditionDTO;
import com.lsh.vivo.bean.request.user.UserConditionVO;
import com.lsh.vivo.bean.request.user.UserSaveVO;
import com.lsh.vivo.bean.response.UserVO;
import com.lsh.vivo.bean.response.role.RoleSelectedVO;
import com.lsh.vivo.bean.response.system.BasePageVO;
import com.lsh.vivo.bean.response.user.UserPageVO;
import com.lsh.vivo.entity.User;
import com.lsh.vivo.enumerate.BaseResultCodeEnum;
import com.lsh.vivo.enumerate.SystemEnum;
import com.lsh.vivo.exception.BaseRequestErrorException;
import com.lsh.vivo.service.UserService;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户模块控制器
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 0:22
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * 模块前缀
     */
    private static final String MODULE_PREFIX = "user:";

    @Operation(summary = "查询某个用户信息", description = "不授权限控制")
    @ApiOperationSupport(order = 1)
    @GetMapping("/{id}")
    public UserVO getById(@NotNull @Valid @PathVariable("id") String id) {
        User user = userService.getByIdWithRelations(id);
        return BeanUtil.copyProperties(user, UserVO.class);
    }

    @Operation(summary = "获取模块下当前用户有的权限", description = "无授权限制")
    @ApiOperationSupport(order = 3)
    @GetMapping("/per")
    public List<String> getPermissions() {
        return userService.listPermissions(MODULE_PREFIX);
    }

    @Operation(summary = "查询用户列表", description = "授权限控制，user:view - 查看用户列表权限, user:* - 用户模块全部权限")
    @ApiOperationSupport(order = 5)
    @PreAuthorize("hasAuthority('user:view') || hasAuthority('user:*')")
    @GetMapping
    public UserPageVO listPageable(@NotNull @ParameterObject @Valid UserConditionVO condition) {
        Page<User> page = new Page<>(condition.getPage(), condition.getSize());
        UserConditionDTO userConditionDTO = BeanUtil.copyProperties(condition, UserConditionDTO.class);
        Page<User> userPage = userService.page(page, userConditionDTO);
        return BeanUtil.copyProperties(userPage, UserPageVO.class);
    }


    @Operation(summary = "新建用户", description = "授权限控制，user:save - 新建用户权限, user:* - 角色模块全部权限")
    @PreAuthorize("hasAuthority('user:save') || hasAuthority('user:*')")
    @ApiOperationSupport(order = 7)
    @PostMapping
    public UserVO save(@RequestBody @Validated UserSaveVO userSaveVO) {
        checkAdmin(userSaveVO.getRoles());
        User newUser = BeanUtil.copyProperties(userSaveVO, User.class);
        newUser.setSys(SystemEnum.F.name());
        userService.save(newUser);
        return BeanUtil.copyProperties(newUser, UserVO.class);
    }

    /**
     * 校验角色不可为超级管理员
     */
    private void checkAdmin(List<RoleSelectedVO> roles) {
        String specialName = "超级管理员";
        roles.forEach(role -> {
            if (specialName.equals(role.getName())) {
                throw new BaseRequestErrorException(BaseResultCodeEnum.ERROR_ROLE_BINDING);
            }
        });
    }
}
