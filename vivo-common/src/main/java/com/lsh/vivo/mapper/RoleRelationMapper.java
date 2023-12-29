package com.lsh.vivo.mapper;

import com.lsh.vivo.entity.RoleRelation;
import com.lsh.vivo.mapper.system.CommonMapper;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import static com.lsh.vivo.entity.table.RoleRelationTableDef.ROLE_RELATION;
import static com.mybatisflex.core.query.QueryMethods.select;

/**
 * @author ASUS
 * @description 针对表【role_relation(角色关联信息)】的数据库操作Mapper
 * @createDate 2023-11-23 14:11:04
 * @Entity com.lsh.vivo.entity.RoleRelation
 */
@Mapper
public interface RoleRelationMapper extends CommonMapper<RoleRelation> {

    /**
     * 查询权限代码
     *
     * @param roleId 角色ID
     * @return
     */
    default List<String> listFunctionAuth(String roleId) {
        QueryWrapper queryWrapper = select(ROLE_RELATION.RELATION_CODE)
                .from(ROLE_RELATION.getTableName())
                .where(ROLE_RELATION.ROLE_ID.eq(roleId));
        return selectListByQueryAs(queryWrapper, String.class);
    }

    /**
     * 查询权限代码
     *
     * @param roleIds 角色ID
     * @return
     */
    default List<String> listFunctionAuth(List<String> roleIds) {
        QueryWrapper queryWrapper = select(ROLE_RELATION.RELATION_CODE)
                .from(ROLE_RELATION.getTableName())
                .where(ROLE_RELATION.ROLE_ID.in(roleIds));
        return selectListByQueryAs(queryWrapper, String.class);
    }
}




