package com.lsh.vivo.service.impl;

import com.lsh.vivo.service.UserAddressService;
import com.lsh.vivo.entity.UserAddress;
import com.lsh.vivo.mapper.UserAddressMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【user_address】的数据库操作Service实现
* @createLocalDateTime 2023-10-28 22:52:04
*/
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress>
    implements UserAddressService {

}




