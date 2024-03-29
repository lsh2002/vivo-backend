package com.lsh.vivo.service;

import com.lsh.vivo.entity.UserAddress;
import com.lsh.vivo.service.system.CommonService;

import java.util.List;

/**
 * @author ASUS
 * @description 针对表【user_address】的数据库操作Service
 * @createLocalDateTime 2023-10-28 22:52:04
 */
public interface UserAddressService extends CommonService<UserAddress> {

    List<UserAddress> listByUserId(String userId);

    void updateStatus(String userId, String id);

    UserAddress getByUserId(String userId);
}
