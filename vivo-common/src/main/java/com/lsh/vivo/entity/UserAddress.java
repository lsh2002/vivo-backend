package com.lsh.vivo.entity;

import com.lsh.vivo.annotation.TableIdPrefix;
import com.lsh.vivo.entity.system.BaseEntity;
import com.lsh.vivo.listener.CustomFlexListener;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author ASUS
 */
@EqualsAndHashCode(callSuper = true)
@TableIdPrefix("USA")
@Table(value = "user_address",
        onInsert = CustomFlexListener.class,
        onUpdate = CustomFlexListener.class)
@Data
public class UserAddress extends BaseEntity implements Serializable, Cloneable {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 收货人
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 收货地址
     */
    private String address;

    @Override
    public UserAddress clone() {
        UserAddress cloneEntity = null;
        try {
            cloneEntity = (UserAddress) super.clone();
        } catch (CloneNotSupportedException ignore) {

        }
        return cloneEntity;
    }
}