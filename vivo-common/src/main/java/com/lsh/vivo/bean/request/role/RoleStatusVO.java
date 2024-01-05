package com.lsh.vivo.bean.request.role;

import com.lsh.vivo.bean.response.system.BaseUpdateVO;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色状态请求参数
 *
 * @author pjw
 * @version 1.0.0
 * @since 2023-10-10 15:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "角色状态请求参数", name = "角色状态请求参数")
public class RoleStatusVO extends BaseUpdateVO {

    /**
     * 启用状态，I:有效，H:无效
     */
    @NotNull(message = "状态不能为空")
    private CommonStatusEnum status;
}
