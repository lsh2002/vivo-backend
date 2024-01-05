package com.lsh.vivo.bean.request.user;

import com.lsh.vivo.bean.response.system.BaseEntityIdVO;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * UserStatusVO类
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/10/4 10:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserStatusVO extends BaseEntityIdVO {

    @NotNull(message = "状态不能为空！")
    private CommonStatusEnum status;

    /**
     * 乐观锁
     */
    @Schema(description = "信息变更记录号", title = "信息变更记录号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "信息变更记录号值不能为空")
    private Integer revision;
}
