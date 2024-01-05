package com.lsh.vivo.bean.request.role;

import com.lsh.vivo.enumerate.CommonStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 角色查询条件
 *
 * @author pjw
 * @version 1.0.0
 * @since 2023-10-03 17:01
 */
@Data
@Schema(description = "角色查询条件", name = "角色查询条件")
public class RoleConditionVO {

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String name;

    /**
     * 启用状态，I:有效，H:无效
     */
    private CommonStatusEnum status;
}
