package com.lsh.vivo.service;

import com.lsh.vivo.bean.dto.user.UserConditionDTO;
import com.lsh.vivo.entity.User;
import com.lsh.vivo.service.system.CommonService;
import com.mybatisflex.core.paginate.Page;

/**
 * @author ASUS
 * @description 针对表【user(用户)】的数据库操作Service
 * @createLocalDateTime 2023-10-28 22:52:02application.yml
 */
public interface UserService extends CommonService<User> {

    /**
     * 根据id查询用户信息，并查询用户角色信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    User getByIdWithRelations(String id);

    /**
     * 根据token里的id查询用户基本信息数据
     *
     * @return 用户基本信息
     */
    User getBasicInfo();

    /**
     * 分页查询用户信息
     *
     * @param page             分页对象
     * @param userConditionDTO 用户条件
     */
    Page<User> page(Page<User> page, UserConditionDTO userConditionDTO);

    boolean updatePass(String userId, String oldPass, String newPass, String checkPass);
}
