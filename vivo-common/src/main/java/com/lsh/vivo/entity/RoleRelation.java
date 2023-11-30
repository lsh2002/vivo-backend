package com.lsh.vivo.entity;

import java.io.Serializable;
import java.util.Date;

import com.lsh.vivo.annotation.TableIdPrefix;
import com.lsh.vivo.entity.system.BaseEntity;
import com.lsh.vivo.listener.CustomFlexListener;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色关联信息
 * @author ASUS
 */
@EqualsAndHashCode(callSuper = true)
@TableIdPrefix("ROR")
@Table(value = "role_relation",
        onInsert = CustomFlexListener.class,
        onUpdate = CustomFlexListener.class)
@Data
public class RoleRelation extends BaseEntity implements Serializable, Cloneable {

    /**
     * 角色Id
     */
    private String roleId;

    /**
     * 关联代码
     */
    private String relationCode;

    @Override
    public RoleRelation clone() {
        RoleRelation cloneEntity = null;
        try {
            cloneEntity = (RoleRelation) super.clone();
        } catch (CloneNotSupportedException ignore) {

        }
        return cloneEntity;
    }
}