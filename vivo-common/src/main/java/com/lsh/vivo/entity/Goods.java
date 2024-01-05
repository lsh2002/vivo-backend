package com.lsh.vivo.entity;

import com.lsh.vivo.annotation.TableIdPrefix;
import com.lsh.vivo.entity.system.BaseEntity;
import com.lsh.vivo.listener.CustomFlexListener;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 商品
 *
 * @TableName goods
 */
@EqualsAndHashCode(callSuper = true)
@Table(value = "goods",
        onInsert = CustomFlexListener.class,
        onUpdate = CustomFlexListener.class
)
@TableIdPrefix("GOD")
@Data
public class Goods extends BaseEntity implements Serializable, Cloneable {

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品类别id
     */
    private String categoryId;

    /**
     * 商品类别
     */
    private String category;

    /**
     * 描述
     */
    private String description;

    @Override
    public Goods clone() {
        Goods cloneEntity = null;
        try {
            cloneEntity = (Goods) super.clone();
        } catch (CloneNotSupportedException ignore) {

        }
        return cloneEntity;
    }
}