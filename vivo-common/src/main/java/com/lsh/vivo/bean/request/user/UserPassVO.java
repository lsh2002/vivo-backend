package com.lsh.vivo.bean.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * @author lsh
 * @version 1.0
 * @since 2023-09-14 16:24
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserPassVO {

    @NotBlank(message = "用户不存在")
    private String userId;

    /**
     * 密码
     */
    @NotBlank(message = "旧密码不能为空")
    private String oldPass;

    /**
     * 密码
     */
    @NotBlank(message = "新密码不能为空")
    private String newPass;

    /**
     * 密码
     */
    @NotBlank(message = "确认密码不能为空")
    private String checkPass;
}
