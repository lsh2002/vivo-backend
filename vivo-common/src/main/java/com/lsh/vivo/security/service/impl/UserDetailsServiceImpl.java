package com.lsh.vivo.security.service.impl;

import com.lsh.vivo.bean.security.UserDetail;
import com.lsh.vivo.entity.Role;
import com.lsh.vivo.entity.User;
import com.lsh.vivo.mapper.RoleRelationMapper;
import com.lsh.vivo.mapper.UserMapper;
import com.lsh.vivo.util.BeanCopyUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CustomUserDetailsService类
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/17 23:34
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;
    private final RoleRelationMapper roleRelationMapper;

    @Override
    public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        User user = userMapper.findByNicknameOrPhone(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " 用户不存在");
        }
        UserDetail userDetail = BeanCopyUtils.copyBean(user, UserDetail.class);
        if (CollectionUtils.isEmpty(user.getRoles())) {
            return userDetail;
        }
        List<String> roleIds = user.getRoles().stream()
                .map(Role::getId)
                .toList();
        List<String> functions = roleRelationMapper.listFunctionAuth(roleIds);
        if (CollectionUtils.isNotEmpty(functions)) {
            userDetail.setAuthorities(AuthorityUtils.createAuthorityList(functions));
        }
        return userDetail;
    }
}