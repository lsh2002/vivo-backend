package com.lsh.vivo.mapper.struct;

import com.lsh.vivo.bean.request.address.AddressSaveVO;
import com.lsh.vivo.bean.request.address.AddressUpdateVO;
import com.lsh.vivo.bean.response.address.UserAddressVO;
import com.lsh.vivo.entity.UserAddress;
import com.lsh.vivo.util.MapperStructTypeConvert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 角色mapper
 *
 * @author pjw
 * @version 1.0.0
 * @since 2023-10-03 16:53
 */
@Mapper(uses = {MapperStructTypeConvert.class})
public interface UserAddressMpp {

    /**
     * 定义实例
     */
    UserAddressMpp INSTANCE = Mappers.getMapper(UserAddressMpp.class);

    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "localDateTimeToLong")
    @Mapping(target = "modifierTime", source = "modifierTime", qualifiedByName = "localDateTimeToLong")
    UserAddressVO toVO(UserAddress UserAddress);

    List<UserAddressVO> toVO(List<UserAddress> UserAddresss);

    UserAddress toDO(AddressSaveVO addressSaveVO);

    UserAddress toDO(AddressUpdateVO addressUpdateVO);
}
