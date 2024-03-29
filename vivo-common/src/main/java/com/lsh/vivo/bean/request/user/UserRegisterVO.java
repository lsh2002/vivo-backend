package com.lsh.vivo.bean.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * @author lsh
 * @version 1.0
 * @since 2023-09-14 16:24
 */
@Data
public class UserRegisterVO {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 密码
     */
    @NotBlank(message = "重复密码不能为空")
    private String checkPass;
}
