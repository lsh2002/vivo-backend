package com.lsh.vivo.bean.response.role;

import com.lsh.vivo.bean.response.system.BaseEntityVO;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.enumerate.SystemEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * 角色信息
 *
 * @author pjw
 * @version 1.0.0
 * @since 2023-10-03 16:39
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
@ToString(callSuper = true)
public class RoleVO extends BaseEntityVO {
    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空！")
    private String name;

    /**
     * 是否系统的
     */
    private SystemEnum sys;

    /**
     * 状态
     */
    private CommonStatusEnum status;
}