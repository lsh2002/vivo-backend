package com.lsh.vivo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.lsh.vivo.bean.dto.role.RolePermChangeDTO;
import com.lsh.vivo.bean.request.role.*;
import com.lsh.vivo.bean.response.role.RoleSelectedVO;
import com.lsh.vivo.bean.response.role.RoleVO;
import com.lsh.vivo.entity.Role;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.enumerate.SystemEnum;
import com.lsh.vivo.mapper.struct.RoleMpp;
import com.lsh.vivo.service.RoleRelationService;
import com.lsh.vivo.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色模块控制器
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 0:22
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    private final RoleRelationService roleRelationService;

    /**
     * 模块前缀
     */
    private static final String MODULE_PREFIX = "role:";

    @Operation(summary = "查询某个角色信息", description = "授权限控制，role:detail - 查看单条记录权限, role:* - 角色模块全部权限")
    @ApiOperationSupport(order = 1)
    @PreAuthorize("hasAuthority('role:detail') || hasAuthority('role:*')")
    @GetMapping("/{id}")
    public RoleVO getById(@NotNull @Valid @PathVariable("id") String id) {
        Role role = roleService.getById(id);
        return RoleMpp.INSTANCE.toVO(role);
    }

    @Operation(summary = "获取模块下当前用户有的权限", description = "获取模块下当前用户有的权限,无授权限制")
    @ApiOperationSupport(order = 5)
    @GetMapping("/per")
    public List<String> getPermissions() {
        return roleService.listPermissions(MODULE_PREFIX);
    }

    @Operation(summary = "查询角色列表信息", description = "授权限控制，role:view - 查询角色列表权限, role:* - 角色模块全部权限")
    @ApiOperationSupport(order = 5)
    @PreAuthorize("hasAuthority('role:view') || hasAuthority('role:*')")
    @GetMapping
    public List<RoleVO> list(@NotNull RoleConditionVO condition) {
        List<Role> roles = roleService.list(condition.getName(), condition.getStatus());
        return RoleMpp.INSTANCE.toVO(roles);
    }

    @Operation(summary = "新建角色", description = "授权限控制，role:save - 新建角色权限, role:* - 角色模块全部权限")
    @ApiOperationSupport(order = 10)
    @PreAuthorize("hasAuthority('role:save') || hasAuthority('role:*')")
    @PostMapping
    public RoleVO save(@RequestBody @NotNull @Valid RoleSaveVO roleSaveVO) {
        Role role = new Role();
        role.setName(roleSaveVO.getName().trim());
        role.setSys(SystemEnum.F.name());
        roleService.save(role);
        return RoleMpp.INSTANCE.toVO(role);
    }

    @Operation(summary = "修改角色名称", description = "授权限控制，role:update - 修改角色名称权限, role:* - 角色模块全部权限")
    @ApiOperationSupport(order = 15)
    @PreAuthorize("hasAuthority('role:update') || hasAuthority('role:*')")
    @PutMapping
    public RoleVO updateById(@RequestBody @NotNull @Valid RoleUpdateVO roleUpdateVO) {
        roleUpdateVO.setName(roleUpdateVO.getName().trim());
        Role newRole = BeanUtil.copyProperties(roleUpdateVO, Role.class);
        roleService.updateById(newRole);
        newRole.setRevision(newRole.getRevision() + 1);
        return RoleMpp.INSTANCE.toVO(newRole);
    }

    @Operation(summary = "修改角色状态", description = "授权限控制，role:update - 修改角色状态权限, role:* - 角色模块全部权限")
    @ApiOperationSupport(order = 20)
    @PreAuthorize("hasAuthority('role:update') || hasAuthority('role:*')")
    @PutMapping("/status")
    public RoleVO changeStatus(@RequestBody @NotNull @Valid RoleStatusVO roleStatusVO) {
        Role role = BeanUtil.copyProperties(roleStatusVO, Role.class);
        roleService.updateById(role);
        role.setRevision(role.getRevision() + 1);
        return RoleMpp.INSTANCE.toVO(role);
    }

    @Operation(summary = "获取角色名下的绑定的权限信息", description = "按角色id查询，授权限控制，role:view - 获取角色名绑定权限信息权限, role:* - 角色模块全部权限")
    @ApiOperationSupport(order = 25)
    @PreAuthorize("hasAuthority('role:view') || hasAuthority('role:*')")
    @GetMapping("/permissions/{id}")
    public List<String> getPermissions(@NotNull @Valid @PathVariable("id") String id) {
        return roleRelationService.getPermissionsById(id);
    }

    @Operation(summary = "修改角色权限信息", description = "授权限控制，role:update - 修改角色权限信息权限, role:* - 角色模块全部权限")
    @ApiOperationSupport(order = 30)
    @PreAuthorize("hasAuthority('role:update') || hasAuthority('role:*')")
    @PutMapping("/updatePermissions")
    public RoleVO updatePermissions(@RequestBody @NotNull @Valid RolePermissionVO rolePermChangeVO) {
        RolePermChangeDTO dto = BeanUtil.copyProperties(rolePermChangeVO, RolePermChangeDTO.class);
        roleRelationService.updatePerms(dto);
        RoleVO role = new RoleVO();
        role.setId(rolePermChangeVO.getId());
        role.setRevision(rolePermChangeVO.getRevision() + 1);
        return role;
    }

    @Operation(summary = "获取角色下拉框数据", description = "不传参数时查询所有状态的角色,传I只查询启用状态角色，不授权限控制")
    @ApiOperationSupport(order = 35)
    @GetMapping("/select")
    public List<RoleSelectedVO> selectList(CommonStatusEnum status) {
        List<Role> roles = roleService.selectList(status);
        return RoleMpp.INSTANCE.toSelectVO(roles);
    }

    @DeleteMapping
    public void removeByIds(@RequestBody @NotEmpty(message = "id集不可为空") @Parameter List<String> ids) {
        roleService.removeByIds(ids);
    }
}
