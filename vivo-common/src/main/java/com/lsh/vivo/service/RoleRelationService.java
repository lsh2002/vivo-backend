package com.lsh.vivo.service;

import com.lsh.vivo.bean.dto.role.RolePermChangeDTO;
import com.lsh.vivo.entity.RoleRelation;
import com.lsh.vivo.service.system.CommonService;

import java.util.List;

/**
 * @author ASUS
 * @description 针对表【role_relation(角色关联信息)】的数据库操作Service
 * @createDate 2023-11-23 14:11:04
 */
public interface RoleRelationService extends CommonService<RoleRelation> {

    /**
     * 根据角色id获取权限
     *
     * @param id 角色id
     * @return 权限集
     */
    List<String> getPermissionsById(String id);

    /**
     * 修改角色所绑定的权限
     *
     * @param rolePermChangeDTO 角色修改权限参数传输类
     */
    void updatePerms(RolePermChangeDTO rolePermChangeDTO);
}
