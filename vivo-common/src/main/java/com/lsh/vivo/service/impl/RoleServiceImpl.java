package com.lsh.vivo.service.impl;

import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.service.RoleService;
import com.lsh.vivo.entity.Role;
import com.lsh.vivo.mapper.RoleMapper;
import com.lsh.vivo.service.system.impl.CommonServiceImpl;
import com.mybatisflex.core.query.If;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lsh.vivo.entity.table.RoleTableDef.ROLE;

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
    public List<Role> selectList(CommonStatusEnum status) {
        String statusVal = status == null ? null : status.name();
        return queryChain()
                .select(ROLE.ID, ROLE.NAME, ROLE.SYS)
                .from(ROLE)
                .where(ROLE.STATUS.eq(statusVal))
                .list();
    }
}




