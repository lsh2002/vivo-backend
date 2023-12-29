package com.lsh.vivo.bean.dto.role;

import lombok.Data;

import java.util.List;

/**
 * 角色修改权限参数传输类
 *
 * @author pjw
 * @version 1.0.0
 * @since 2023-11-15 15:00
 */
@Data
public class RolePermChangeDTO {

    /**
     * 主键
     */
    private String id;

    /**
     * 乐观锁
     */
    private Integer revision;

    /**
     * 修改后删除绑定的权限名集
     */
    private List<String> deletePerms;

    /**
     * 修改后新增绑定的权限名集
     */
    private List<String> savePerms;
}
