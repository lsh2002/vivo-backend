package com.lsh.vivo.service.impl;

import com.lsh.vivo.entity.Role;
import com.lsh.vivo.enumerate.BaseResultCodeEnum;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.exception.BaseRequestErrorException;
import com.lsh.vivo.mapper.RoleMapper;
import com.lsh.vivo.service.RoleService;
import com.lsh.vivo.service.system.impl.CommonServiceImpl;
import com.mybatisflex.core.query.If;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lsh.vivo.entity.table.RoleTableDef.ROLE;
import static com.mybatisflex.core.query.QueryMethods.distinct;
import static com.mybatisflex.core.query.QueryMethods.number;

/**
 * @author ASUS
 * @description 针对表【role(角色信息表)】的数据库操作Service实现
 * @createLocalDateTime 2023-10-28 22:51:56
 */
@Service
public class RoleServiceImpl extends CommonServiceImpl<RoleMapper, Role>
        implements RoleService {

    @Override
    public List<Role> list(String name, CommonStatusEnum status) {
        String statusVal = status == null ? null : status.name();
        return queryChain().select()
                .from(ROLE)
                .where(ROLE.NAME.likeLeft(name, If::hasText))
                .and(ROLE.STATUS.eq(statusVal, If::hasText))
                .list();
    }

    @Override
    public boolean save(Role entity) {
        // 校验角色是否存在
        String queryName = queryChain().select(number(1))
                .from(ROLE)
                .where(ROLE.NAME.eq(entity.getName()))
                .limit(1).oneAs(String.class);
        if (existByCondition(queryName)) {
            throw new BaseRequestErrorException(BaseResultCodeEnum.ERROR_ROLE_NAME_EXIST);
        }
        return super.save(entity);
    }

    @Override
    public List<Role> selectList(CommonStatusEnum status) {
        String statusVal = status == null ? null : status.name();
        return queryChain()
                .select(distinct(ROLE.ID, ROLE.NAME, ROLE.SYS))
                .from(ROLE)
                .where(ROLE.STATUS.eq(statusVal))
                .where(ROLE.NAME.isNotNull())
                .where(ROLE.NAME.ne(""))
                .list();
    }
}




