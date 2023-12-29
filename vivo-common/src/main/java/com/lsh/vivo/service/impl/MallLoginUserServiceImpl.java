package com.lsh.vivo.service.impl;

import com.lsh.vivo.entity.User;
import com.lsh.vivo.mapper.UserMapper;
import com.lsh.vivo.service.MallLoginUserService;
import com.lsh.vivo.service.system.impl.CommonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author ASUS
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createLocalDateTime 2023-10-28 22:52:02
 */
@Service
@RequiredArgsConstructor
public class MallLoginUserServiceImpl extends CommonServiceImpl<UserMapper, User> {

}




