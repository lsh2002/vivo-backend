package com.lsh.vivo.bean.request.user;

import com.lsh.vivo.bean.response.role.RoleSelectedVO;
import com.lsh.vivo.validator.Mobile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.util.List;


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
    @Length(min = 3, max = 12, message = "账号长度超过限制长度")
    private String username;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空！")
    @Length(min = 4, max = 12, message = "昵称长度不符")
    private String nickname;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 角色
     */
    @NotEmpty(message = "角色不能为空！")
    private List<RoleSelectedVO> roles;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空！")
    @Mobile(message = "手机号格式不正确！需为11位数字")
    private String phone;
}
