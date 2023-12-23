package com.lsh.vivo.bean.response.role;

import com.lsh.vivo.enumerate.SystemEnum;
import lombok.Data;

/**
 * RoleSelectedVO类
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/10/2 21:53
 */
@Data
public class RoleSelectedVO {

    /**
     * 主键
     */
    private String id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 是否系统的
     */
    private SystemEnum sys;
}
