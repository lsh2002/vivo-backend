package com.lsh.vivo.mapper;

import com.lsh.vivo.entity.User;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.relation.RelationManager;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ASUS
 * @description 针对表【user(用户)】的数据库操作Mapper
 * @createLocalDateTime 2023-10-29 21:03:08
 * @Entity com.lsh.vivo.pojo.entity.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据昵称或手机号查询用户
     *
     * @param username
     * @return
     */
    default User findByNicknameOrPhone(String username) {
//        String regex = "^\\d{11}$";
//        QueryCondition condition;
//        // 邮箱包含@
//        if (username.matches(regex)) {
//            // 手机号11位数字
//            condition = USER.MOBILE.eq(account);
//        } else {
//            condition = USER.NICKNAME.eq(account);
//        }
//        QueryWrapper where = QueryWrapper.create().select(USER.ID, USER.PASSWORD, USER.NICKNAME, USER.NAME,
//                        USER.LOGIN_START_TIME, USER.ACCOUNT_ERROR_COUNT, USER.STATUS, USER.REVISION).from(USER)
//                .where(condition).limit(1);
//        RelationManager.addIgnoreRelations("depts");
//        return selectOneWithRelationsByQuery(where);
        return null;
    }
}




