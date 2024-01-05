package com.lsh.vivo.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.lsh.vivo.bean.constant.GlobalConstant;
import com.lsh.vivo.bean.dto.user.UserConditionDTO;
import com.lsh.vivo.bean.request.user.UserConditionVO;
import com.lsh.vivo.bean.request.user.UserSaveVO;
import com.lsh.vivo.bean.request.user.UserStatusVO;
import com.lsh.vivo.bean.response.role.RoleSelectedVO;
import com.lsh.vivo.bean.response.system.PageVO;
import com.lsh.vivo.bean.response.user.UserBasicInfoVO;
import com.lsh.vivo.bean.response.user.UserVO;
import com.lsh.vivo.entity.User;
import com.lsh.vivo.enumerate.BaseResultCodeEnum;
import com.lsh.vivo.enumerate.SystemEnum;
import com.lsh.vivo.exception.BaseRequestErrorException;
import com.lsh.vivo.mapper.struct.UserMpp;
import com.lsh.vivo.service.UserService;
import com.lsh.vivo.util.OauthContext;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

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
        return UserMpp.INSTANCE.toVO(user);
    }

    @Operation(summary = "查询用户基本信息", description = "查询用户基本信息,无授权限制")
    @ApiOperationSupport(order = 2)
    @GetMapping("/info")
    public UserBasicInfoVO getBasicInfo() {
        return getCommonBasicInfo();
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
    public PageVO<UserVO> listPageable(@NotNull @ParameterObject @Valid UserConditionVO condition) {
        Page<User> page = new Page<>(condition.getPage(), condition.getSize());
        UserConditionDTO userConditionDTO = UserMpp.INSTANCE.toDTO(condition);
        Page<User> userPage = userService.page(page, userConditionDTO);
        return UserMpp.INSTANCE.toPageVO(userPage);
    }

    @Operation(summary = "新建用户", description = "授权限控制，user:save - 新建用户权限, user:* - 角色模块全部权限")
    @PreAuthorize("hasAuthority('user:save') || hasAuthority('user:*')")
    @ApiOperationSupport(order = 7)
    @PostMapping
    public UserVO save(@RequestBody @Validated UserSaveVO userSaveVO) {
        checkAdmin(userSaveVO.getRoles());
        User newUser = UserMpp.INSTANCE.toUser(userSaveVO);
        newUser.setSys(SystemEnum.F.name());
        userService.save(newUser);
        return UserMpp.INSTANCE.toVO(newUser);
    }

    @Operation(summary = "注销/恢复账号", description = "授权限控制,user:update - 用户状态修改权限 user:* - 用户模块全部权限")
    @ApiOperationSupport(order = 10)
    @PreAuthorize("hasAuthority('user:update') || hasAuthority('user:*')")
    @PutMapping("/status")
    public UserVO changedStatus(@RequestBody @NotNull @Validated UserStatusVO userStatusVO) {
        String userId = (String) OauthContext.get(GlobalConstant.HTTP_USER_ID);
        if (Objects.equals(userId, userStatusVO.getId())) {
            throw new BaseRequestErrorException(BaseResultCodeEnum.ERROR_ILLEGAL_ACCESS);
        }
        User newUser = UserMpp.INSTANCE.toDO(userStatusVO);
        userService.updateById(newUser);
        newUser.setRevision(newUser.getRevision() + 1);
        return UserMpp.INSTANCE.toVO(newUser);
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

    /**
     * 统一获取用户基本信息
     */
    private UserBasicInfoVO getCommonBasicInfo() {
        User user = userService.getBasicInfo();
        // 页面查看权限后缀
        String suffix = ":view";
        String suffixAll = ":*";
        // 超级管理员需要的请求记录查询权限
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> perms = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .filter(authority -> (authority.endsWith(suffix) || authority.endsWith(suffixAll)))
                .toList();
        return UserMpp.INSTANCE.toInfoVO(user, perms);
    }
}
