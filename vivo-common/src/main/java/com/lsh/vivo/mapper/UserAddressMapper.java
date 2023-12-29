package com.lsh.vivo.mapper;

import com.lsh.vivo.entity.UserAddress;
import com.lsh.vivo.mapper.system.CommonMapper;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ASUS
 * @description 针对表【user_address】的数据库操作Mapper
 * @createLocalDateTime 2023-10-29 21:03:10
 * @Entity com.lsh.vivo.pojo.entity.UserAddress
 */
@Mapper
public interface UserAddressMapper extends CommonMapper<UserAddress> {

}




