package com.lsh.vivo.entity;

import com.lsh.vivo.annotation.TableIdPrefix;
import com.lsh.vivo.entity.system.BaseEntity;
import com.lsh.vivo.listener.CustomFlexListener;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户和角色关联表
 *
 * @author ASUS
 */
@EqualsAndHashCode(callSuper = true)
@TableIdPrefix("USR")
@Table(value = "user_role",
        onInsert = CustomFlexListener.class,
        onUpdate = CustomFlexListener.class)
@Data
public class UserRole extends BaseEntity implements Serializable, Cloneable {

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;

    @Override
    public UserRole clone() {
        UserRole cloneEntity = null;
        try {
            cloneEntity = (UserRole) super.clone();
        } catch (CloneNotSupportedException ignore) {

        }
        return cloneEntity;
    }
}