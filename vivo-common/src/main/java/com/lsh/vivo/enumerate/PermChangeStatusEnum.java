package com.lsh.vivo.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态：绑定角色是否权限变更
 *
 * @author pjw
 * @version 1.0.0
 * @since 2023-11-23 15:21
 */
@Getter
@AllArgsConstructor
public enum PermChangeStatusEnum {

    /**
     * 权限变更了
     */
    T("权限发生变更"),

    /**
     * 权限没有变更
     */
    F("权限没有发生变更");

    private final String msg;
}
