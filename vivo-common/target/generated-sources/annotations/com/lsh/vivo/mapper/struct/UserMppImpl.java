package com.lsh.vivo.mapper.struct;

import com.lsh.vivo.bean.dto.user.UserConditionDTO;
import com.lsh.vivo.bean.request.user.UserConditionVO;
import com.lsh.vivo.bean.request.user.UserRegisterVO;
import com.lsh.vivo.bean.request.user.UserSaveVO;
import com.lsh.vivo.bean.request.user.UserStatusVO;
import com.lsh.vivo.bean.request.user.UserUpdateVO;
import com.lsh.vivo.bean.response.role.RoleSelectedVO;
import com.lsh.vivo.bean.response.system.PageResult;
import com.lsh.vivo.bean.response.system.PageVO;
import com.lsh.vivo.bean.response.user.UserBasicInfoVO;
import com.lsh.vivo.bean.response.user.UserVO;
import com.lsh.vivo.bean.security.UserDetail;
import com.lsh.vivo.entity.Role;
import com.lsh.vivo.entity.User;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.enumerate.SystemEnum;
import com.lsh.vivo.util.MapperStructTypeConvert;
import com.mybatisflex.core.paginate.Page;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-18T01:12:39+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
public class UserMppImpl implements UserMpp {

    private final MapperStructTypeConvert mapperStructTypeConvert = new MapperStructTypeConvert();

    @Override
    public UserDetail toUserDetail(User user) {
        if ( user == null ) {
            return null;
        }

        UserDetail userDetail = new UserDetail();

        userDetail.setId( user.getId() );
        userDetail.setPassword( user.getPassword() );
        userDetail.setUsername( user.getUsername() );
        userDetail.setNickname( user.getNickname() );
        userDetail.setStatus( user.getStatus() );

        return userDetail;
    }

    @Override
    public UserVO toVO(User user) {
        if ( user == null ) {
            return null;
        }

        UserVO userVO = new UserVO();

        userVO.setCreateTime( mapperStructTypeConvert.localDateTimeToLong( user.getCreateTime() ) );
        userVO.setModifierTime( mapperStructTypeConvert.localDateTimeToLong( user.getModifierTime() ) );
        userVO.setId( user.getId() );
        userVO.setRevision( user.getRevision() );
        userVO.setCreatorId( user.getCreatorId() );
        userVO.setCreator( user.getCreator() );
        userVO.setModifierId( user.getModifierId() );
        userVO.setModifier( user.getModifier() );
        userVO.setNickname( user.getNickname() );
        userVO.setUsername( user.getUsername() );
        userVO.setPhone( user.getPhone() );
        userVO.setRoles( roleListToRoleSelectedVOList( user.getRoles() ) );
        if ( user.getStatus() != null ) {
            userVO.setStatus( Enum.valueOf( CommonStatusEnum.class, user.getStatus() ) );
        }

        return userVO;
    }

    @Override
    public PageVO<UserVO> toPageVO(Page<User> userPage) {
        if ( userPage == null ) {
            return null;
        }

        PageVO<UserVO> pageVO = new PageVO<UserVO>();

        pageVO.setPage( userPageToPageResult( userPage ) );
        if ( userPage.hasRecords() ) {
            pageVO.setResults( userListToUserVOList( userPage.getRecords() ) );
        }

        return pageVO;
    }

    @Override
    public User toDO(UserStatusVO userStatusVO) {
        if ( userStatusVO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userStatusVO.getId() );
        if ( userStatusVO.getStatus() != null ) {
            user.setStatus( userStatusVO.getStatus().name() );
        }
        user.setRevision( userStatusVO.getRevision() );

        return user;
    }

    @Override
    public User toUser(UserSaveVO userSave) {
        if ( userSave == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( userSave.getUsername() );
        user.setNickname( userSave.getNickname() );
        user.setPassword( userSave.getPassword() );
        user.setPhone( userSave.getPhone() );
        user.setRoles( roleSelectedVOListToRoleList( userSave.getRoles() ) );

        return user;
    }

    @Override
    public User toUser(UserUpdateVO userUpdate) {
        if ( userUpdate == null ) {
            return null;
        }

        User user = new User();

        user.setRevision( userUpdate.getRevision() );
        user.setUsername( userUpdate.getUsername() );
        user.setNickname( userUpdate.getNickname() );
        user.setPhone( userUpdate.getPhone() );

        return user;
    }

    @Override
    public UserConditionDTO toDTO(UserConditionVO condition) {
        if ( condition == null ) {
            return null;
        }

        UserConditionDTO userConditionDTO = new UserConditionDTO();

        userConditionDTO.setUsername( condition.getUsername() );
        userConditionDTO.setNickname( condition.getNickname() );
        userConditionDTO.setRoleId( condition.getRoleId() );
        userConditionDTO.setStatus( condition.getStatus() );

        return userConditionDTO;
    }

    @Override
    public UserBasicInfoVO toInfoVO(User user, List<String> perms) {
        if ( user == null && perms == null ) {
            return null;
        }

        UserBasicInfoVO userBasicInfoVO = new UserBasicInfoVO();

        if ( user != null ) {
            userBasicInfoVO.setId( user.getId() );
            userBasicInfoVO.setUsername( user.getUsername() );
            userBasicInfoVO.setNickname( user.getNickname() );
            userBasicInfoVO.setRevision( user.getRevision() );
        }
        List<String> list = perms;
        if ( list != null ) {
            userBasicInfoVO.setPerms( new ArrayList<String>( list ) );
        }

        return userBasicInfoVO;
    }

    @Override
    public User toUser(UserRegisterVO user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setUsername( user.getUsername() );
        user1.setPassword( user.getPassword() );

        return user1;
    }

    protected RoleSelectedVO roleToRoleSelectedVO(Role role) {
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

    protected List<RoleSelectedVO> roleListToRoleSelectedVOList(List<Role> list) {
        if ( list == null ) {
            return null;
        }

        List<RoleSelectedVO> list1 = new ArrayList<RoleSelectedVO>( list.size() );
        for ( Role role : list ) {
            list1.add( roleToRoleSelectedVO( role ) );
        }

        return list1;
    }

    protected PageResult userPageToPageResult(Page<User> page) {
        if ( page == null ) {
            return null;
        }

        PageResult pageResult = new PageResult();

        pageResult.setTotal( page.getTotalRow() );
        pageResult.setSize( page.getPageSize() );
        pageResult.setCurrent( page.getPageNumber() );

        return pageResult;
    }

    protected List<UserVO> userListToUserVOList(List<User> list) {
        if ( list == null ) {
            return null;
        }

        List<UserVO> list1 = new ArrayList<UserVO>( list.size() );
        for ( User user : list ) {
            list1.add( toVO( user ) );
        }

        return list1;
    }

    protected Role roleSelectedVOToRole(RoleSelectedVO roleSelectedVO) {
        if ( roleSelectedVO == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleSelectedVO.getId() );
        role.setName( roleSelectedVO.getName() );
        if ( roleSelectedVO.getSys() != null ) {
            role.setSys( roleSelectedVO.getSys().name() );
        }

        return role;
    }

    protected List<Role> roleSelectedVOListToRoleList(List<RoleSelectedVO> list) {
        if ( list == null ) {
            return null;
        }

        List<Role> list1 = new ArrayList<Role>( list.size() );
        for ( RoleSelectedVO roleSelectedVO : list ) {
            list1.add( roleSelectedVOToRole( roleSelectedVO ) );
        }

        return list1;
    }
}
