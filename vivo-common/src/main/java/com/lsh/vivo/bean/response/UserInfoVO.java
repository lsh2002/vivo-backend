package com.lsh.vivo.bean.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lsh
 * @version 1.0
 * @since 2023-09-14 16:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVO {
    /**
     * 主键
     */
    private String userId;

    /**
     * 账号
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 乐观锁值
     */
    private Integer revision;
}
