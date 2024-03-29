package com.lsh.vivo.service.impl;

import com.lsh.vivo.bean.constant.GlobalConstant;
import com.lsh.vivo.bean.dto.user.UserConditionDTO;
import com.lsh.vivo.entity.User;
import com.lsh.vivo.enumerate.BaseResultCodeEnum;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.enumerate.SystemEnum;
import com.lsh.vivo.event.user.bean.UserSaveEvent;
import com.lsh.vivo.exception.BaseRequestErrorException;
import com.lsh.vivo.mapper.UserMapper;
import com.lsh.vivo.provider.CustomPasswordEncoder;
import com.lsh.vivo.service.UserService;
import com.lsh.vivo.service.system.impl.CommonServiceImpl;
import com.lsh.vivo.util.OauthContext;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.If;
import com.mybatisflex.core.query.QueryMethods;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import static com.lsh.vivo.entity.table.UserRoleTableDef.USER_ROLE;
import static com.lsh.vivo.entity.table.UserTableDef.USER;
import static com.mybatisflex.core.query.QueryMethods.*;

/**
 * @author ASUS
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createLocalDateTime 2023-10-28 22:52:02
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CommonServiceImpl<UserMapper, User> implements UserService {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final CustomPasswordEncoder customPasswordEncoder;

    @Override
    public User getByIdWithRelations(String id) {
        return mapper.selectOneWithRelationsById(id);
    }

    @Override
    public User getBasicInfo() {
        String id = (String) OauthContext.get(GlobalConstant.HTTP_USER_ID);
        QueryWrapper wrapper = select(USER.ID, USER.USERNAME, USER.NICKNAME, USER.REVISION)
                .from(USER)
                .where(USER.ID.eq(id));
        return mapper.selectOneByQuery(wrapper);
    }

    @Override
    public Page<User> page(Page<User> page, UserConditionDTO condition) {
        String status = condition.getStatus() == null ? "" : condition.getStatus().name();
        QueryWrapper queryWrapper = query()
                .select(USER.ALL_COLUMNS)
                .from(USER)
                .where(USER.USERNAME.likeLeft(condition.getUsername(), If::hasText))
                .and(USER.NICKNAME.likeLeft(condition.getNickname(), If::hasText))
                .and(USER.STATUS.eq(status, If::hasText))
                .orderBy(USER.CREATE_TIME.desc(), USER.ID.desc());
        if (StringUtils.isNotBlank(condition.getRoleId())) {
            queryWrapper.and(QueryMethods.exists(
                            selectOne().from(USER_ROLE).where(USER_ROLE.USER_ID.eq(USER.ID))
                                    .and(USER_ROLE.ROLE_ID.eq(condition.getRoleId()))
                    )
            );
        }
        return mapper.paginateWithRelations(page, queryWrapper);
    }

    @Override
    public boolean updatePass(String userId, String oldPass, String newPass, String checkPass) {
        boolean exists = queryChain().select(USER.ID)
                .where(USER.ID.eq(userId))
                .and(USER.PASSWORD.eq(customPasswordEncoder.encode(oldPass)))
                .exists();
        if (!exists) {
            throw new BaseRequestErrorException(BaseResultCodeEnum.ERROR_USER_PASSWORD_ERROR);
        }
        updateChain()
                .set(USER.PASSWORD, customPasswordEncoder.encode(newPass))
                .where(USER.ID.eq(userId))
                .update();
        return true;
    }

    @Override
    public boolean save(User entity) {
        // 校验昵称是否存在
        String queryName = queryChain().select(number(1))
                .from(USER)
                .where(USER.NICKNAME.eq(entity.getUsername()))
                .limit(1).oneAs(String.class);
        if (existByCondition(queryName)) {
            throw new BaseRequestErrorException(BaseResultCodeEnum.ERROR_USER_USERNAME_EXIST);
        }
        // aes加密  操作失败返回空字符串-密码不合法
        String encodePwd = customPasswordEncoder.encode(entity.getPassword());
        if ("".equals(encodePwd)) {
            throw new BaseRequestErrorException(BaseResultCodeEnum.ERROR_INVALID_PASSWORD);
        }
        entity.setNickname(entity.getUsername());
        entity.setPassword(encodePwd);
        entity.setStatus(CommonStatusEnum.I.name());
        entity.setSys(SystemEnum.F.name());
        // 插入用户，事件监听绑定联系
        super.save(entity);
        UserSaveEvent saveEvent = new UserSaveEvent(this);
        saveEvent.setUser(entity);
        applicationEventPublisher.publishEvent(saveEvent);
        return true;
    }
}




