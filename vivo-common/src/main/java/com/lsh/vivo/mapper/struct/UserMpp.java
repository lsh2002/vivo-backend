package com.lsh.vivo.mapper.struct;

import com.lsh.vivo.bean.dto.user.UserConditionDTO;
import com.lsh.vivo.bean.request.user.*;
import com.lsh.vivo.bean.response.system.PageVO;
import com.lsh.vivo.bean.response.user.UserBasicInfoVO;
import com.lsh.vivo.bean.response.user.UserVO;
import com.lsh.vivo.bean.security.UserDetail;
import com.lsh.vivo.entity.User;
import com.lsh.vivo.util.MapperStructTypeConvert;
import com.mybatisflex.core.paginate.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户mapper
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/20 22:15
 */
@Mapper(uses = {MapperStructTypeConvert.class})
public interface UserMpp {

    /**
     * 定义实例
     */
    UserMpp INSTANCE = Mappers.getMapper(UserMpp.class);

    /**
     * 转登录用户信息
     *
     * @param user 用户
     * @return 返回登录用户信息
     */
    UserDetail toUserDetail(User user);

    /**
     * 转前端交互用户
     *
     * @param user 用户
     * @return 返回前端交互用户
     */
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "localDateTimeToLong")
    @Mapping(target = "modifierTime", source = "modifierTime", qualifiedByName = "localDateTimeToLong")
    UserVO toVO(User user);

    /**
     * 转前端交互用户集
     *
     * @param userPage 分页信息
     * @return 返回前端交互用户
     */
    @Mapping(target = "page.total", source = "totalRow")
    @Mapping(target = "page.size", source = "pageSize")
    @Mapping(target = "page.current", source = "pageNumber")
    @Mapping(target = "results", source = "records")
    PageVO<UserVO> toPageVO(Page<User> userPage);

    /**
     * 用户状态修改对象转数据库用户实体对象
     *
     * @param userStatusVO 前端状态修改对象
     * @return 数据库用户实体
     */
    User toDO(UserStatusVO userStatusVO);

    /**
     * 转DO对象
     *
     * @param userSave 用户
     * @return 返回前端交互用户
     */

    User toUser(UserSaveVO userSave);

    /**
     * 转DO对象
     *
     * @param userUpdate 用户
     * @return 返回前端交互用户
     */
    User toUser(UserUpdateVO userUpdate);


    /**
     * 转dto条件对象
     *
     * @param condition 前端vo条件对象
     * @return 除分页的dto条件对象
     */
    UserConditionDTO toDTO(UserConditionVO condition);


    /**
     * 数据库用户对象转前端基本对象
     *
     * @param user  数据库对象
     * @param perms 用户拥有的页面查看权限
     * @return 前端基本用户信息
     */
    UserBasicInfoVO toInfoVO(User user, List<String> perms);

    User toUser(UserRegisterVO user);
}
