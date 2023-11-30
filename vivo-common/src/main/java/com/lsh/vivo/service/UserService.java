package com.lsh.vivo.service;

import com.lsh.vivo.entity.User;
import com.lsh.vivo.bean.response.UserInfoVO;
import com.mybatisflex.core.service.IService;

/**
 * @author ASUS
 * @description 针对表【user(用户)】的数据库操作Service
 * @createLocalDateTime 2023-10-28 22:52:02application.yml
 */
public interface UserService extends IService<User> {

    /**
     * 查询用户名是否存在
     *
     * @param username 用户名
     * @return
     */
    boolean findUsernameExist(String username);

    /**
     * 获取用户信息
     *
     * @return
     */
    UserInfoVO getInfo();
}
