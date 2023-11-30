package com.lsh.vivo.service;

import com.lsh.vivo.bean.response.MallUserLoginVO;


public interface MallLoginUserService {

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return MallUserLoginVO
     */
    MallUserLoginVO login(String username, String password);

    /**
     * 用户登出
     */
    boolean logout();
}
