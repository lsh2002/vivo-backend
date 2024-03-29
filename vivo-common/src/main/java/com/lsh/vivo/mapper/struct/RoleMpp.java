package com.lsh.vivo.mapper.struct;

import com.lsh.vivo.bean.dto.role.RolePermChangeDTO;
import com.lsh.vivo.bean.request.role.RolePermissionVO;
import com.lsh.vivo.bean.request.role.RoleStatusVO;
import com.lsh.vivo.bean.request.role.RoleUpdateVO;
import com.lsh.vivo.bean.response.role.RoleSelectedVO;
import com.lsh.vivo.bean.response.role.RoleVO;
import com.lsh.vivo.entity.Role;
import com.lsh.vivo.util.MapperStructTypeConvert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 角色mapper
 *
 * @author pjw
 * @version 1.0.0
 * @since 2023-10-03 16:53
 */
@Mapper(uses = {MapperStructTypeConvert.class})
public interface RoleMpp {

    /**
     * 定义实例
     */
    RoleMpp INSTANCE = Mappers.getMapper(RoleMpp.class);

    /**
     * 转前端交互角色
     *
     * @param role 角色
     * @return 返回前端交互角色
     */
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "localDateTimeToLong")
    @Mapping(target = "modifierTime", source = "modifierTime", qualifiedByName = "localDateTimeToLong")
    RoleVO toVO(Role role);

    /**
     * 转前端交互角色
     *
     * @param roles 多个角色
     * @return 返回前端交互角色
     */
    List<RoleVO> toVO(List<Role> roles);

    /**
     * 转数据库角色
     *
     * @param roleVO 前端交互角色
     * @return 数据库角色
     */
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "longToLocalDateTime")
    @Mapping(target = "modifierTime", source = "modifierTime", qualifiedByName = "longToLocalDateTime")
    Role toDO(RoleVO roleVO);

    /**
     * 角色修改对象转角色实体对象
     *
     * @param roleUpdateVO 前端修改对象
     * @return 数据库对象
     */
    Role toDO(RoleUpdateVO roleUpdateVO);

    /**
     * 前端状态参数转角色对象
     *
     * @param statusVO 状态参数
     * @return 角色对象
     */
    Role toDO(RoleStatusVO statusVO);

    /**
     * 角色对象转下拉框数据对象，id,name
     *
     * @param role 角色对象
     * @return 下拉框对象
     */
    RoleSelectedVO toSelectVO(Role role);

    /**
     * 角色对象转下拉框对象
     *
     * @param roles 角色对象
     * @return 下拉框对象
     */
    List<RoleSelectedVO> toSelectVO(List<Role> roles);

    /**
     * 角色权限修改，前端参数实体转传输实体类
     *
     * @param permsVO 前端交互对象
     * @return 传输实体
     */
    RolePermChangeDTO toDTO(RolePermissionVO permsVO);
}
