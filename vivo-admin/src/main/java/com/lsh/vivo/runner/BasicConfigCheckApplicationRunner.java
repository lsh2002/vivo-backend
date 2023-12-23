package com.lsh.vivo.runner;

import cn.hutool.core.bean.BeanUtil;
import com.lsh.vivo.bean.constant.GlobalConstant;
import com.lsh.vivo.bean.request.user.UserSaveVO;
import com.lsh.vivo.bean.response.role.RoleSelectedVO;
import com.lsh.vivo.entity.Role;
import com.lsh.vivo.entity.RoleRelation;
import com.lsh.vivo.entity.User;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.enumerate.SystemEnum;
import com.lsh.vivo.mapper.RoleRelationMapper;
import com.lsh.vivo.provider.ApplicationContextProvider;
import com.lsh.vivo.service.RoleRelationService;
import com.lsh.vivo.service.RoleService;
import com.lsh.vivo.service.UserRoleService;
import com.lsh.vivo.service.UserService;
import com.lsh.vivo.util.OauthContext;
import com.lsh.vivo.util.RSAUtils;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.lsh.vivo.entity.table.RoleRelationTableDef.ROLE_RELATION;
import static com.lsh.vivo.entity.table.RoleTableDef.ROLE;
import static com.lsh.vivo.entity.table.UserRoleTableDef.USER_ROLE;
import static com.lsh.vivo.entity.table.UserTableDef.USER;
import static com.mybatisflex.core.query.QueryMethods.select;


/**
 * 超级管理员账号密码管理
 *
 * @author lsh
 * @version 1.0.0
 * @since 2023-12-21 10:37
 **/
@Slf4j
@Order(1)
@Component
public class BasicConfigCheckApplicationRunner implements ApplicationRunner {

    @Value("${project.root.name}")
    private String rootName;

    @Value("${project.root.pass}")
    private String rootPassword;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            if (StringUtils.isBlank(rootName)) {
                rootName = "admin";
            }
            if (StringUtils.isBlank(rootPassword)) {
                rootPassword = "123456";
            }
            OauthContext.set(GlobalConstant.HTTP_USER_ID, "SYSTEM");
            OauthContext.set(GlobalConstant.HTTP_USER, "系统生成");
            RoleService roleService = ApplicationContextProvider.getBean(RoleService.class);
            QueryWrapper queryWrapper = select(ROLE.ID)
                    .from(ROLE)
                    .where(ROLE.NAME.eq("超级管理员"))
                    .limit(1);
            String roleId = roleService.getObjAs(queryWrapper, String.class);
            if (StringUtils.isBlank(roleId)) {
                Role superRoot = new Role();
                superRoot.setName("超级管理员");
                superRoot.setSys(SystemEnum.T.name());
                superRoot.setStatus(CommonStatusEnum.I.name());
                superRoot.setRevision(1);
                superRoot.setCreator("系统生成");
                superRoot.setCreatorId("SYSTEM");
                superRoot.setCreateTime(LocalDateTime.now());
                roleService.save(superRoot);
                roleId = superRoot.getId();
            }
            List<String> functions = new ArrayList<>(List.of("user:*", "role:*", "product:*", "product-cate:*", "product-sku:*", "order:*"));
            RoleRelationService roleRelationService = ApplicationContextProvider.getBean(RoleRelationService.class);
            queryWrapper = select(ROLE_RELATION.RELATION_CODE)
                    .from(ROLE_RELATION)
                    .where(ROLE_RELATION.ROLE_ID.eq(roleId));
            List<String> hasFunctions = roleRelationService.listAs(queryWrapper, String.class);
            if (hasFunctions == null) {
                hasFunctions = new ArrayList<>(0);
            }
            Collection<String> intersections = CollectionUtils.intersection(functions, hasFunctions);
            functions.removeAll(intersections);
            hasFunctions.removeAll(intersections);
            RoleRelationMapper roleRelationMapper = ApplicationContextProvider.getBean(RoleRelationMapper.class);
            if (CollectionUtils.isNotEmpty(functions)) {
                // 新增额外的绑定权限
                String finalRoleId = roleId;
                List<RoleRelation> roleRelations = functions.stream().map(perm -> {
                    RoleRelation relation = new RoleRelation();
                    relation.setRoleId(finalRoleId);
                    relation.setRelationCode(perm);
                    relation.setRevision(1);
                    relation.setCreator("系统生成");
                    relation.setCreatorId("SYSTEM");
                    relation.setCreateTime(LocalDateTime.now());
                    return relation;
                }).toList();
                roleRelationMapper.insertBatch(roleRelations);
            }
            if (CollectionUtils.isNotEmpty(hasFunctions)) {
                // 删去不再绑定的权限
                QueryWrapper wrapper = select()
                        .from(ROLE_RELATION)
                        .where(ROLE_RELATION.ROLE_ID.eq(roleId))
                        .and(ROLE_RELATION.RELATION_CODE.in(hasFunctions));
                roleRelationMapper.deleteByQuery(wrapper);
            }

            UserRoleService userRoleRelationService = ApplicationContextProvider.getBean(UserRoleService.class);
            queryWrapper = select(USER_ROLE.USER_ID)
                    .from(USER_ROLE)
                    .where(USER_ROLE.ROLE_ID.eq(roleId)).limit(1);
            String userId = userRoleRelationService.getObjAs(queryWrapper, String.class);

            UserService userService = ApplicationContextProvider.getBean(UserService.class);
            queryWrapper = QueryWrapper.create().where(USER.ID.eq(userId));
            boolean exists = userId != null && userService.exists(queryWrapper);
            if (!exists) {
                UserSaveVO userSaveVO = new UserSaveVO();
                userSaveVO.setUsername(rootName);
                userSaveVO.setMobile("");

                RoleSelectedVO roleSelectedVO = new RoleSelectedVO();
                roleSelectedVO.setId(roleId);
                userSaveVO.setRoles(List.of(roleSelectedVO));

                RSAUtils aesUtils = ApplicationContextProvider.getBean(RSAUtils.class);
                User user = BeanUtil.copyProperties(userSaveVO, User.class);
                user.setNickname("超级管理员");
                user.setPassword(aesUtils.encryption(rootPassword));
                user.setStatus(CommonStatusEnum.I.name());
                user.setSys(SystemEnum.T.name());
                user.setRevision(1);
                user.setCreator("系统生成");
                user.setCreatorId("SYSTEM");
                user.setCreateTime(LocalDateTime.now());
                userService.save(user);
            }
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
