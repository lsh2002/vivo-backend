package com.lsh.vivo.bean.response;

import com.lsh.vivo.bean.response.system.BaseEntityVO;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * UserVO
 *
 * @author lsh
 * @version 1.0.0
 * @since 2023-09-14 16:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO extends BaseEntityVO {
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
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 状态
     */
    private CommonStatusEnum status;
}
