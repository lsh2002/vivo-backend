package com.lsh.vivo.bean.response.address;

import com.lsh.vivo.bean.response.system.BaseEntityVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ASUS
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserAddressVO extends BaseEntityVO {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 收货人
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 收货地址
     */
    private String isDefault;
}