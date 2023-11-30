package com.lsh.vivo.bean.response.system;

import lombok.Data;

/**
 * 分页属性
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/8/22 22:07
 */
@Data
public class PageResult {

    /**
     * 当前页码
     */
    private long current;

    /**
     * 每页显示数
     */
    private long size;

    /**
     * 总数
     */
    private long total;
}
