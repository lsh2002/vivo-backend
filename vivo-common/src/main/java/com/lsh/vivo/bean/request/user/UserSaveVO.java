package com.lsh.vivo.bean.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * @author lsh
 * @version 1.0
 * @since 2023-09-14 16:24
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveVO {

    /**
     * 用户名
     */
    @Pattern(regexp = "^(?!\\d+$)[\\da-zA-z$_]{3,12}$", message = "账号不能是纯数字，仅能输入大小写字母、特殊字符_$ 和数字，且长度3-12位")
    @NotBlank(message = "账号不能为空！")
    @Length(min=3, max = 12, message = "账号长度超过限制长度")
    private String username;

    /**
     * 昵称
     */
    @NotBlank(message = "用户名不能为空！")
    @Length(min=4, max=12,message = "用户名长度不符")
    private String nickname;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 重复密码
     */
    @NotBlank(message = "重复密码不能为空")
    private String checkPassword;
}
