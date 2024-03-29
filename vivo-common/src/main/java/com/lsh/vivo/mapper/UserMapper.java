package com.lsh.vivo.mapper;

import com.lsh.vivo.entity.User;
import com.lsh.vivo.mapper.system.CommonMapper;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import static com.lsh.vivo.entity.table.UserTableDef.USER;

/**
 * 针对表【user(用户)】的数据库操作Mapper
 *
 * @author ASUS
 * 2023-10-29 21:03:08
 */
@Mapper
public interface UserMapper extends CommonMapper<User> {

    /**
     * 根据账号查询用户
     *
     * @param account 账号
     */
    default User findByUsernameOrPhone(String account) {
        String regex = "^\\d{11}$";
        QueryCondition condition;
        // 邮箱包含@
        if (account.matches(regex)) {
            // 手机号11位数字
            condition = USER.PHONE.eq(account);
        } else {
            condition = USER.USERNAME.eq(account);
        }
        QueryWrapper where = QueryWrapper.create()
                .select(USER.ID, USER.USERNAME, USER.NICKNAME, USER.PASSWORD, USER.STATUS, USER.REVISION)
                .from(USER)
                .where(condition)
                .limit(1);
        return selectOneWithRelationsByQuery(where);
    }
}




