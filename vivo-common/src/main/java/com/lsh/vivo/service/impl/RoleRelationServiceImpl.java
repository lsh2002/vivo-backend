package com.lsh.vivo.service.impl;

import com.lsh.vivo.bean.dto.role.RolePermChangeDTO;
import com.lsh.vivo.entity.Role;
import com.lsh.vivo.entity.RoleRelation;
import com.lsh.vivo.enumerate.BaseResultCodeEnum;
import com.lsh.vivo.enumerate.SystemEnum;
import com.lsh.vivo.exception.BaseRequestErrorException;
import com.lsh.vivo.mapper.RoleRelationMapper;
import com.lsh.vivo.service.RoleRelationService;
import com.lsh.vivo.service.system.impl.CommonServiceImpl;
import com.lsh.vivo.util.OauthContext;
import com.mybatisflex.core.query.If;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lsh.vivo.bean.constant.GlobalConstant.HTTP_USER_ID;
import static com.lsh.vivo.entity.table.RoleRelationTableDef.ROLE_RELATION;
import static com.lsh.vivo.entity.table.RoleTableDef.ROLE;
import static com.lsh.vivo.entity.table.UserRoleTableDef.USER_ROLE;
import static com.lsh.vivo.entity.table.UserTableDef.USER;
import static com.mybatisflex.core.query.QueryMethods.*;

/**
 * @author ASUS
 * @description 针对表【role_relation(角色关联信息)】的数据库操作Service实现
 * @createDate 2023-11-23 14:11:04
 */
@Service
public class RoleRelationServiceImpl extends CommonServiceImpl<RoleRelationMapper, RoleRelation>
        implements RoleRelationService {

    @Override
    public List<String> getPermissionsById(String id) {
        return queryChain()
                .select(distinct(ROLE_RELATION.RELATION_CODE))
                .from(ROLE_RELATION)
                .where(ROLE_RELATION.ROLE_ID.eq(id, If::hasText))
                .and(ROLE_RELATION.RELATION_CODE.isNotNull())
                .and(ROLE_RELATION.RELATION_CODE.ne(""))
                .listAs(String.class);
    }

    @Override
    public void updatePerms(RolePermChangeDTO rolePermChangeDTO) {
        // 不允许修改自己角色权限
        String oneAs = queryChain().select(number(1))
                .from(USER_ROLE)
                .where(USER_ROLE.ROLE_ID.eq(rolePermChangeDTO.getId()))
                .where(USER_ROLE.USER_ID.eq(OauthContext.get(HTTP_USER_ID)))
                .limit(1).oneAs(String.class);
        if (existByCondition(oneAs)) {
            throw new BaseRequestErrorException(BaseResultCodeEnum.ERROR_CHANGE_BINDING_ROLE_PERM);
        }
        // 模拟权限修改的乐观锁，多个用户同时修改同一角色权限只有一个能成功
        boolean update = UpdateChain.of(Role.class)
                .set(ROLE.REVISION, ROLE.REVISION.add(1))
                .where(ROLE.ID.eq(rolePermChangeDTO.getId()))
                .and(ROLE.REVISION.eq(rolePermChangeDTO.getRevision()))
                .update();
        if (!update) {
            throw new BaseRequestErrorException(BaseResultCodeEnum.ERROR_DATA_CHANGED);
        }

        // 获取到权限修改机会 不可修改超级管理员信息
        String oneAs1 = queryChain().select(number(1))
                .from(ROLE)
                .where(ROLE.ID.eq(rolePermChangeDTO.getId()))
                .and(ROLE.SYS.eq(SystemEnum.T.name()))
                .limit(1)
                .oneAs(String.class);
        if (existByCondition(oneAs1)) {
            // 是超级管理员 则报错
            throw new BaseRequestErrorException(BaseResultCodeEnum.ERROR_IDENTITY_OPERATION);
        }
        if (CollectionUtils.isNotEmpty(rolePermChangeDTO.getSavePerms())) {
            // 新增额外的绑定权限
            List<RoleRelation> roleRelations = rolePermChangeDTO.getSavePerms().stream().map(perm -> {
                RoleRelation relation = new RoleRelation();
                relation.setRoleId(rolePermChangeDTO.getId());
                relation.setRelationCode(perm);
                return relation;
            }).toList();
            mapper.insertBatch(roleRelations);
        }
        if (CollectionUtils.isNotEmpty(rolePermChangeDTO.getDeletePerms())) {
            // 删去不再绑定的权限
            QueryWrapper queryWrapper = select()
                    .where(ROLE_RELATION.ROLE_ID.eq(rolePermChangeDTO.getId()))
                    .and(ROLE_RELATION.RELATION_CODE.in(rolePermChangeDTO.getDeletePerms()));
            mapper.deleteByQuery(queryWrapper);
        }
    }
}




