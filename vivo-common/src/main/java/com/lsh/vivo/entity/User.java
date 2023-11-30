package com.lsh.vivo.entity;

import com.lsh.vivo.annotation.TableIdPrefix;
import com.lsh.vivo.entity.system.BaseEntity;
import com.lsh.vivo.listener.CustomFlexListener;
import com.mybatisflex.annotation.RelationManyToMany;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 用户
 *
 * @author ASUS
 */
@EqualsAndHashCode(callSuper = true)
@TableIdPrefix("USR")
@Table(value = "user",
        onInsert = CustomFlexListener.class,
        onUpdate = CustomFlexListener.class)
@Data
public class User extends BaseEntity implements Serializable, Cloneable {

    /**
     * 账号
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 权限变更状态
     */
    private String permStatus;

    /**
     * 角色
     */
    @RelationManyToMany(
            joinTable = "user_role",
            selfField = "id", joinSelfColumn = "user_id",
            targetField = "id", joinTargetColumn = "role_id",

            selectColumns = {"id", "name"},
            extraCondition = "(status='I')"
    )
    private List<Role> roles;

    @Override
    public User clone() {
        User cloneEntity = null;
        try {
            cloneEntity = (User) super.clone();
        } catch (CloneNotSupportedException ignore) {

        }
        return cloneEntity;
    }
}