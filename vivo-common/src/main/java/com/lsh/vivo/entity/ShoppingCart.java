package com.lsh.vivo.entity;

import com.lsh.vivo.annotation.TableIdPrefix;
import com.lsh.vivo.entity.system.BaseEntity;
import com.lsh.vivo.listener.CustomFlexListener;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 购物车
 *
 * @author ASUS
 */
@EqualsAndHashCode(callSuper = true)
@TableIdPrefix("SPC")
@Table(value = "shopping_cart",
        onInsert = CustomFlexListener.class,
        onUpdate = CustomFlexListener.class)
@Data
public class ShoppingCart extends BaseEntity implements Serializable, Cloneable {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer num;

    @Override
    public ShoppingCart clone() {
        ShoppingCart cloneEntity = null;
        try {
            cloneEntity = (ShoppingCart) super.clone();
        } catch (CloneNotSupportedException ignore) {

        }
        return cloneEntity;
    }
}