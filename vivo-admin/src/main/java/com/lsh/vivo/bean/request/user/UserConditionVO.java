package com.lsh.vivo.bean.request.user;

import com.lsh.vivo.bean.request.system.PageRequest;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.validation.annotation.Validated;

/**
 * 用户分页查询条件
 *
 * @author pjw
 * @version 1.0.0
 * @since 2023-09-27 15:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Validated
@Schema(description = "用户查询条件", name = "用户查询条件")
public class UserConditionVO extends PageRequest {

    /**
     * 用户名
     */
    @Schema(description = "用户名", title = "用户名")
    private String username;

    /**
     * 昵称
     */
    @Schema(description = "昵称", title = "昵称")
    private String nickname;

    /**
     * 角色id
     */
    @Schema(description = "角色id", title = "角色id")
    private String roleId;

    /**
     * 启用状态，I:有效，H:无效
     */
    private CommonStatusEnum status;
}
