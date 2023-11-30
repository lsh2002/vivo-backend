package com.lsh.vivo.service.system;


import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;

import java.util.List;

/**
 * service基类
 *
 * @author lsh
 * @version 1.0.0
 * @since 2023-11-22 11:06
 */
public interface CommonService<T> extends IService<T> {

    /**
     * wrapper封装好查询表及其查询列条件，默认select 1,不可手动添加select条件
     *
     * @param wrapper 包装条件
     * @return 存在记录true，反之false
     */
    boolean existByWrapper(QueryWrapper wrapper);

    List<String> listPermissions(String... prefixs);
}
