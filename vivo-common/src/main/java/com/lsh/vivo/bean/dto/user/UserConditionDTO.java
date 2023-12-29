package com.lsh.vivo.bean.dto.user;

import com.lsh.vivo.enumerate.CommonStatusEnum;
import lombok.Data;

/**
 * UserConditionDTO类
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/10/2 8:40
 */
@Data
public class UserConditionDTO {

    /**
     * 按照用户名
     */
    private String username;

    /**
     * 按照昵称
     */
    private String nickname;

    /**
     * 按照角色ID
     */
    private String roleId;

    /**
     * 按照状态
     */
    private CommonStatusEnum status;
}
