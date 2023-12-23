package com.lsh.vivo.entity;

import com.lsh.vivo.annotation.TableIdPrefix;
import com.lsh.vivo.entity.system.BaseEntity;
import com.lsh.vivo.listener.CustomFlexListener;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 角色信息表
 *
 * @author lsh
 */
@EqualsAndHashCode(callSuper = true)
@TableIdPrefix("ROL")
@Table(value = "role",
        onInsert = CustomFlexListener.class,
        onUpdate = CustomFlexListener.class)
@Data
public class Role extends BaseEntity implements Serializable, Cloneable {

    /**
     * 角色名
     */
    private String name;

    /**
     * 是否系统的;T-系统预留不允许删除，F-自定义
     */
    private String sys;

    @Override
    public Role clone() {
        Role cloneEntity = null;
        try {
            cloneEntity = (Role) super.clone();
        } catch (CloneNotSupportedException ignore) {

        }
        return cloneEntity;
    }
}