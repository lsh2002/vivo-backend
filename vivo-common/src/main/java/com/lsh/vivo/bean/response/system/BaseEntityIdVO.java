package com.lsh.vivo.bean.response.system;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基础返回对象,id属性
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/13 20:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseEntityIdVO extends BaseVO {
    /**
     * 主键
     */
    private String id;
}
