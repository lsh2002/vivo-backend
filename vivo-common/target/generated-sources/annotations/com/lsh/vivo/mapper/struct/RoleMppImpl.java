package com.lsh.vivo.mapper.struct;

import com.lsh.vivo.bean.dto.role.RolePermChangeDTO;
import com.lsh.vivo.bean.request.role.RolePermissionVO;
import com.lsh.vivo.bean.request.role.RoleStatusVO;
import com.lsh.vivo.bean.request.role.RoleUpdateVO;
import com.lsh.vivo.bean.response.role.RoleSelectedVO;
import com.lsh.vivo.bean.response.role.RoleVO;
import com.lsh.vivo.entity.Role;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.enumerate.SystemEnum;
import com.lsh.vivo.util.MapperStructTypeConvert;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-18T01:12:39+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
public class RoleMppImpl implements RoleMpp {

    private final MapperStructTypeConvert mapperStructTypeConvert = new MapperStructTypeConvert();

    @Override
    public RoleVO toVO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleVO roleVO = new RoleVO();

        roleVO.setCreateTime( mapperStructTypeConvert.localDateTimeToLong( role.getCreateTime() ) );
        roleVO.setModifierTime( mapperStructTypeConvert.localDateTimeToLong( role.getModifierTime() ) );
        roleVO.setId( role.getId() );
        roleVO.setRevision( role.getRevision() );
        roleVO.setCreatorId( role.getCreatorId() );
        roleVO.setCreator( role.getCreator() );
        roleVO.setModifierId( role.getModifierId() );
        roleVO.setModifier( role.getModifier() );
        roleVO.setName( role.getName() );
        if ( role.getSys() != null ) {
            roleVO.setSys( Enum.valueOf( SystemEnum.class, role.getSys() ) );
        }
        if ( role.getStatus() != null ) {
            roleVO.setStatus( Enum.valueOf( CommonStatusEnum.class, role.getStatus() ) );
        }

        return roleVO;
    }

    @Override
    public List<RoleVO> toVO(List<Role> roles) {
        if ( roles == null ) {
            return null;
        }

        List<RoleVO> list = new ArrayList<RoleVO>( roles.size() );
        for ( Role role : roles ) {
            list.add( toVO( role ) );
        }

        return list;
    }

    @Override
    public Role toDO(RoleVO roleVO) {
        if ( roleVO == null ) {
            return null;
        }

        Role role = new Role();

        role.setCreateTime( mapperStructTypeConvert.longToLocalDateTime( roleVO.getCreateTime() ) );
        role.setModifierTime( mapperStructTypeConvert.longToLocalDateTime( roleVO.getModifierTime() ) );
        role.setId( roleVO.getId() );
        if ( roleVO.getStatus() != null ) {
            role.setStatus( roleVO.getStatus().name() );
        }
        role.setRevision( roleVO.getRevision() );
        role.setCreatorId( roleVO.getCreatorId() );
        role.setCreator( roleVO.getCreator() );
        role.setModifierId( roleVO.getModifierId() );
        role.setModifier( roleVO.getModifier() );
        role.setName( roleVO.getName() );
        if ( roleVO.getSys() != null ) {
            role.setSys( roleVO.getSys().name() );
        }

        return role;
    }

    @Override
    public Role toDO(RoleUpdateVO roleUpdateVO) {
        if ( roleUpdateVO == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleUpdateVO.getId() );
        role.setRevision( roleUpdateVO.getRevision() );
        role.setName( roleUpdateVO.getName() );

        return role;
    }

    @Override
    public Role toDO(RoleStatusVO statusVO) {
        if ( statusVO == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( statusVO.getId() );
        if ( statusVO.getStatus() != null ) {
            role.setStatus( statusVO.getStatus().name() );
        }
        role.setRevision( statusVO.getRevision() );

        return role;
    }

    @Override
    public RoleSelectedVO toSelectVO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleSelectedVO roleSelectedVO = new RoleSelectedVO();

        roleSelectedVO.setId( role.getId() );
        roleSelectedVO.setName( role.getName() );
        if ( role.getSys() != null ) {
            roleSelectedVO.setSys( Enum.valueOf( SystemEnum.class, role.getSys() ) );
        }

        return roleSelectedVO;
    }

    @Override
    public List<RoleSelectedVO> toSelectVO(List<Role> roles) {
        if ( roles == null ) {
            return null;
        }

        List<RoleSelectedVO> list = new ArrayList<RoleSelectedVO>( roles.size() );
        for ( Role role : roles ) {
            list.add( toSelectVO( role ) );
        }

        return list;
    }

    @Override
    public RolePermChangeDTO toDTO(RolePermissionVO permsVO) {
        if ( permsVO == null ) {
            return null;
        }

        RolePermChangeDTO rolePermChangeDTO = new RolePermChangeDTO();

        rolePermChangeDTO.setId( permsVO.getId() );
        rolePermChangeDTO.setRevision( permsVO.getRevision() );
        List<String> list = permsVO.getDeletePerms();
        if ( list != null ) {
            rolePermChangeDTO.setDeletePerms( new ArrayList<String>( list ) );
        }
        List<String> list1 = permsVO.getSavePerms();
        if ( list1 != null ) {
            rolePermChangeDTO.setSavePerms( new ArrayList<String>( list1 ) );
        }

        return rolePermChangeDTO;
    }
}
