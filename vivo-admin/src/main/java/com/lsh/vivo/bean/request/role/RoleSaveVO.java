package com.lsh.vivo.bean.request.role;

import com.lsh.vivo.bean.response.system.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

/**
 * 角色新增对象类
 *
 * @author pjw
 * @version 1.0.0
 * @since 2023-10-10 15:16
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
@Schema(description = "新建角色信息", name = "新建角色信息")
public class RoleSaveVO extends BaseVO {

    /**
     * 角色名称
     */
    @Schema(description = "角色名称", title = "角色名称，12字数内", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "角色名称不能为空！")
    @Length(max = 12, message = "角色名称超过限制长度")
    private String name;
}
