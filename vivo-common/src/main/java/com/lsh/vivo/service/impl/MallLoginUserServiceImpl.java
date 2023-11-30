package com.lsh.vivo.service.impl;

import com.lsh.vivo.bean.response.MallUserLoginVO;
import com.lsh.vivo.bean.response.UserInfoVO;
import com.lsh.vivo.bean.security.UserDetail;
import com.lsh.vivo.service.MallLoginUserService;
import com.lsh.vivo.util.BeanCopyUtils;
import com.lsh.vivo.util.RedisCache;
import com.lsh.vivo.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import static com.lsh.vivo.bean.constant.RedisKey.STORE_LOGIN_USER;

/**
 * @author ASUS
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createLocalDateTime 2023-10-28 22:52:02
 */
@Service
@RequiredArgsConstructor
public class MallLoginUserServiceImpl implements MallLoginUserService {

    private final AuthenticationManager authenticationManager;

    private final RedisCache redisCache;

    @Override
    public MallUserLoginVO login(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 获取userid 生成token
        UserDetail userDetail = (UserDetail) authenticate.getPrincipal();

        return null;
    }

    @Override
    public boolean logout() {
        // 获取token 解析获取userId
        String userId = SecurityUtils.getUserId();
        // 删除redis中的用户信息
        return redisCache.deleteObject(STORE_LOGIN_USER + userId);
    }
}




