package com.lsh.vivo.bean.request.role;

import com.lsh.vivo.bean.response.system.BaseUpdateVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 角色修改后-绑定的权限
 *
 * @author pjw
 * @version 1.0.0
 * @since 2023-10-03 23:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Validated
@Schema(description = "角色绑定权限修改请求参数", name = "角色绑定权限修改请求参数")
public class RolePermissionVO extends BaseUpdateVO {

    /**
     * 修改后删除绑定的权限名集
     */
    @Schema(description = "删去的权限名集", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> deletePerms;

    /**
     * 修改后新增绑定的权限名集
     */
    @Schema(description = "新增的权限名集", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> savePerms;
}
