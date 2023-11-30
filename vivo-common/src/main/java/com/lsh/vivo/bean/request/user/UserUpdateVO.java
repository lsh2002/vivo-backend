package com.lsh.vivo.bean.request.user;

import com.lsh.vivo.validator.Mobile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


/**
 * @author lsh
 * @version 1.0
 * @since 2023-09-14 16:24
 */
@Data
public class UserUpdateVO {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    /**
     * 手机号
     */
    @Mobile
    @Length(max = 11, message = "手机号超过限制长度")
    private String phone;

    /**
     * 乐观锁值
     */
    @NotNull
    private Integer revision;
}
