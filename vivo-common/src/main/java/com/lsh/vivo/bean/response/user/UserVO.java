package com.lsh.vivo.bean.response.user;

import com.lsh.vivo.bean.response.role.RoleSelectedVO;
import com.lsh.vivo.bean.response.system.BaseEntityVO;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * UserVO
 *
 * @author lsh
 * @version 1.0.0
 * @since 2023-09-14 16:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO extends BaseEntityVO {
    /**
     * 昵称
     */
    @Schema(description = "昵称", title = "昵称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String nickname;

    /**
     * 用户名
     */
    @Schema(description = "用户名", title = "用户名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String username;

    /**
     * 手机号
     */
    @Schema(description = "手机号", title = "手机号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String phone;


    /**
     * 角色
     */
    @Schema(description = "角色", title = "角色", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<RoleSelectedVO> roles;

    /**
     * 状态
     */
    private CommonStatusEnum status;
}
