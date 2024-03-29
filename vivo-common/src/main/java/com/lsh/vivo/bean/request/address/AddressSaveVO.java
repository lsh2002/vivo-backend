package com.lsh.vivo.bean.request.address;

import com.lsh.vivo.bean.request.system.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ASUS
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AddressSaveVO extends BaseRequest {

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


    private String isDefault;
}