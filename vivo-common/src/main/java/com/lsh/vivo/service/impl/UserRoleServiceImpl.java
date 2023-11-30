package com.lsh.vivo.service.impl;

import com.lsh.vivo.entity.Role;
import com.lsh.vivo.service.UserRoleService;
import com.lsh.vivo.entity.UserRole;
import com.lsh.vivo.mapper.UserRoleMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author ASUS
* @description 针对表【user_role(用户和角色关联表)】的数据库操作Service实现
* @createLocalDateTime 2023-10-28 22:52:06
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService {

    @Override
    public List<Role> findUserRolesById(String id) {
        return null;
    }
}