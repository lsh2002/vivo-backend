package com.lsh.vivo.bean.response.system;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * BaseUpdateVO类
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/10/2 12:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseUpdateVO extends BaseEntityIdVO {
    /**
     * 乐观锁
     */
    private Integer revision;
}
