package com.lsh.vivo.service;

import com.lsh.vivo.entity.Role;
import com.lsh.vivo.entity.UserRole;
import com.mybatisflex.core.service.IService;

import java.util.List;

/**
* @author ASUS
* @description 针对表【user_role(用户和角色关联表)】的数据库操作Service
* @createLocalDateTime 2023-10-28 22:52:06
*/
public interface UserRoleService extends IService<UserRole> {

    /**
     * 根据用户id获取角色
     * @param id
     * @return
     */
    List<Role> findUserRolesById(String id);
}
