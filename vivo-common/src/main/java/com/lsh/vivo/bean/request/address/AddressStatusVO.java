package com.lsh.vivo.bean.request.address;

import com.lsh.vivo.bean.request.system.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ASUS
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AddressStatusVO extends BaseRequest {

    /**
     * 用户id
     */
    private String userId;


    private String id;
}