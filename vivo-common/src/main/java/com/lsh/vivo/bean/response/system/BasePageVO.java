package com.lsh.vivo.bean.response.system;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 基础分页对象属性
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/24 19:21
 */
@Slf4j
@Data
public class BasePageVO<T> {

    /**
     * 分页信息
     */
    private PageResult page;
    /**
     * 数据信息
     */
    private List<T> results;
}
