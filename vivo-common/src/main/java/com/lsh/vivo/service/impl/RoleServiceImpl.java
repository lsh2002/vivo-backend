package com.lsh.vivo.service.impl;

import com.lsh.vivo.service.RoleService;
import com.lsh.vivo.entity.Role;
import com.lsh.vivo.mapper.RoleMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【role(角色信息表)】的数据库操作Service实现
* @createLocalDateTime 2023-10-28 22:51:56
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService {

}




