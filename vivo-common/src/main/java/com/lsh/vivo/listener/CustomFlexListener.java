package com.lsh.vivo.listener;

import com.lsh.vivo.bean.constant.GlobalConstant;
import com.lsh.vivo.entity.system.BaseEntity;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.util.OauthContext;
import com.mybatisflex.annotation.InsertListener;
import com.mybatisflex.annotation.UpdateListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * CustomInsertListener类
 *
 * @author lsh
 * @version 1.0.0
 * 2023/10/2 10:36
 */
@Slf4j
public class CustomFlexListener implements InsertListener, UpdateListener {

    @Override
    public void onInsert(Object o) {
        BaseEntity entity = (BaseEntity) o;
        entity.setRevision(1);
        Object creatorId = entity.getCreatorId();
        if (creatorId == null) {
            String userId = (String) OauthContext.get(GlobalConstant.HTTP_USER_ID);
            if (userId != null) {
                entity.setCreatorId(userId);
            }
        }
        Object creator = entity.getCreator();
        if (creator == null) {
            String user = (String) OauthContext.get(GlobalConstant.HTTP_USER);
            if (user != null) {
                entity.setCreator(user);
            }
        }
        if (StringUtils.isBlank(entity.getStatus())) {
            entity.setStatus(CommonStatusEnum.I.name());
        }
        entity.setCreateTime(LocalDateTime.now());
    }

    @Override
    public void onUpdate(Object o) {
        BaseEntity entity = (BaseEntity) o;
        Object modifierId = entity.getModifierId();
        if (modifierId == null) {
            String userId = (String) OauthContext.get(GlobalConstant.HTTP_USER_ID);
            if (userId != null) {
                entity.setModifierId(userId);
            }
        }
        Object modifier = entity.getModifier();
        if (modifier == null) {
            String user = (String) OauthContext.get(GlobalConstant.HTTP_USER);
            if (user != null) {
                entity.setModifier(user);
            }
        }
        entity.setModifierTime(LocalDateTime.now());
    }
}
