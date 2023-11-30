package com.lsh.vivo.bean.response.system;

import lombok.Data;

/**
 * 基础返回对象
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/7/15 23:05
 */
@Data
public abstract class BaseResult {

    /**
     * 代码
     */
    private String code;

    /**
     * 备注说明
     */
    private String message;

    /**
     * 是否成功
     */
    private boolean success;
}
