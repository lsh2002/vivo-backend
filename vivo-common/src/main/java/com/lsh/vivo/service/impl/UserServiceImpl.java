package com.lsh.vivo.service.impl;

import com.lsh.vivo.bean.response.UserInfoVO;
import com.lsh.vivo.entity.User;
import com.lsh.vivo.mapper.UserMapper;
import com.lsh.vivo.service.UserService;
import com.lsh.vivo.util.BeanCopyUtils;
import com.lsh.vivo.util.SecurityUtils;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author ASUS
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createLocalDateTime 2023-10-28 22:52:02
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public boolean save(User entity) {
//        // 校验数据是否存在
//        existSave(USER.NICKNAME.eq(entity.getNickname()), new AccountExistException());
//        existSave(USER.EMAIL.eq(entity.getEmail()), new EmailExistException());
//        if (StringUtils.isNotBlank(entity.getMobile())) {
//            existSave(USER.MOBILE.eq(entity.getMobile()), new MobileExistException());
//        }
//        // 对密码进行rsa解密再aes加密,操作失败返回空字符串-密码不合法
//        String encodePwd = encoder.encode(entity.getPassword());
//        if ("".equals(encodePwd)) {
//            throw new InvalidPasswordException();
//        }
//        entity.setAccountErrorCount(0);
//        entity.setPassword(encodePwd);
//        return super.save(entity);
        return false;
    }

    /**
     * 检查用户名是否存在
     *
     * @param username 用户名
     * @return true-存在 false-不存在
     */
    @Override
    public boolean findUsernameExist(String username) {
        return false;
    }

    @Override
    public UserInfoVO getInfo() {
        // 获取当前用户id
        String userId = SecurityUtils.getUserId();
        // 根据用户id查询信息
        User user = getById(userId);
        // 封装成UserInfoVo
        return BeanCopyUtils.copyBean(user, UserInfoVO.class);
    }
}




