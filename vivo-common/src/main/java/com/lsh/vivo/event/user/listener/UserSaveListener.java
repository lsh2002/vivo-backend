package com.lsh.vivo.event.user.listener;

import com.lsh.vivo.entity.User;
import com.lsh.vivo.entity.UserRole;
import com.lsh.vivo.event.user.bean.UserSaveEvent;
import com.lsh.vivo.provider.ApplicationContextProvider;
import com.lsh.vivo.service.UserRoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * UserSearchListener类
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/10/1 21:37
 */
@Component
public class UserSaveListener implements ApplicationListener<UserSaveEvent> {

    @Override
    public void onApplicationEvent(UserSaveEvent event) {
        User user = event.getUser();
        if (CollectionUtils.isNotEmpty(user.getRoles())) {
            UserRoleService userRoleService = ApplicationContextProvider.getBean(UserRoleService.class);
            List<UserRole> roleRelations = user.getRoles().stream().map(role -> {
                UserRole userRoleRelation = new UserRole();
                userRoleRelation.setUserId(user.getId());
                userRoleRelation.setRoleId(role.getId());
                return userRoleRelation;
            }).toList();
            userRoleService.saveBatch(roleRelations);
        }
    }
}
