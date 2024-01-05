package com.lsh.vivo.bean.response.user;

import com.lsh.vivo.bean.response.system.BaseEntityIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用户登录后基本显示信息
 *
 * @author pjw
 * @version 1.0.0
 * @since 2023-10-11 13:39
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "用户基本信息", name = "用户基本信息")
public class UserBasicInfoVO extends BaseEntityIdVO {

    /**
     * 用户名
     */
    @Schema(description = "用户名", title = "用户名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String username;

    /**
     * 昵称
     */
    @Schema(description = "昵称", title = "昵称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String nickname;

    /**
     * 乐观锁
     */
    @Schema(description = "信息变更记录号", title = "信息变更记录号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer revision;

    /**
     * 用户拥有的页面查看权限
     */
    @Schema(description = "用户拥有的页面查看权限", title = "用户拥有的页面查看权限", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> perms;
}
