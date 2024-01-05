package com.lsh.vivo.bean.request;

import com.lsh.vivo.bean.request.system.BaseRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 登录请求参数
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/7/9 11:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AdminLoginVO extends BaseRequest {

    /**
     * 用户
     */
    @NotBlank(message = "账号密码不能为空！")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "账号密码不能为空！")
    private String password;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空！")
    private String verifyCode;

    /**
     * 来源
     */
    private String source;
}
