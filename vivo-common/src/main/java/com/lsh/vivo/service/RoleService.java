package com.lsh.vivo.service;

import com.lsh.vivo.entity.Role;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.service.system.CommonService;

import java.util.List;

/**
 * @author ASUS
 * @description 针对表【role(角色信息表)】的数据库操作Service
 * @createLocalDateTime 2023-10-28 22:51:56
 */
public interface RoleService extends CommonService<Role> {

    /**
     * 获取角色列表
     *
     * @param name   角色名称
     * @param status 状态
     * @return 角色列表
     */
    List<Role> list(String name, CommonStatusEnum status);

    /**
     * 获取角色列表
     *
     * @param status 状态
     * @return 角色列表
     */
    List<Role> selectList(CommonStatusEnum status);
}
