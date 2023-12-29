package com.lsh.vivo.mapper;

import com.lsh.vivo.entity.UserRole;
import com.lsh.vivo.mapper.system.CommonMapper;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ASUS
 * @description 针对表【user_role(用户和角色关联表)】的数据库操作Mapper
 * @createLocalDateTime 2023-10-29 21:03:12
 * @Entity com.lsh.vivo.pojo.entity.UserRole
 */
@Mapper
public interface UserRoleMapper extends CommonMapper<UserRole> {

}




