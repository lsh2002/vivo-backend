package com.lsh.vivo.mapper;

import com.lsh.vivo.entity.Role;
import com.lsh.vivo.mapper.system.CommonMapper;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ASUS
 * @description 针对表【role(角色信息表)】的数据库操作Mapper
 * @createLocalDateTime 2023-10-29 21:03:02
 * @Entity com.lsh.vivo.pojo.entity.Role
 */
@Mapper
public interface RoleMapper extends CommonMapper<Role> {

}




