package com.lsh.vivo.entity;

import com.lsh.vivo.annotation.TableIdPrefix;
import com.lsh.vivo.entity.system.BaseEntity;
import com.lsh.vivo.listener.CustomFlexListener;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author lsh
 */
@EqualsAndHashCode(callSuper = true)
@TableIdPrefix("COL")
@Table(value = "collect",
        onInsert = CustomFlexListener.class,
        onUpdate = CustomFlexListener.class
)
@Data
public class Collect extends BaseEntity implements Serializable, Cloneable {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 商品id
     */
    private String productId;

    @Override
    public Collect clone() {
        Collect cloneEntity = null;
        try {
            cloneEntity = (Collect) super.clone();
        } catch (CloneNotSupportedException ignore) {

        }
        return cloneEntity;
    }
}