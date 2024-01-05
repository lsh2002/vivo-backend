package com.lsh.vivo.entity;

import com.lsh.vivo.annotation.TableIdPrefix;
import com.lsh.vivo.entity.system.BaseEntity;
import com.lsh.vivo.listener.CustomFlexListener;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 商品类别
 *
 * @author ASUS
 */
@EqualsAndHashCode(callSuper = true)
@Table(value = "goods_category",
        onInsert = CustomFlexListener.class,
        onUpdate = CustomFlexListener.class
)
@TableIdPrefix("GDC")
@Data
public class GoodsCategory extends BaseEntity implements Serializable, Cloneable {
    /**
     * 商品类别名称
     */
    private String name;

    /**
     * 父类id
     */
    private String parentId;

    /**
     * 父类名称
     */
    private String parentName;

    /**
     * 类目级别
     */
    private String level;

    @Override
    public GoodsCategory clone() {
        GoodsCategory cloneEntity = null;
        try {
            cloneEntity = (GoodsCategory) super.clone();
        } catch (CloneNotSupportedException ignore) {

        }
        return cloneEntity;
    }
}