package com.lsh.vivo.bean.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MallUserLoginVO {

    /**
     * token
     */
    private String token;

    /**
     * 用户信息
     */
    private UserInfoVO userInfoVO;
}