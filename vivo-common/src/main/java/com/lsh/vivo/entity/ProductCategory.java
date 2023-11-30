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
@Table(value = "product_catrgory",
        onInsert = CustomFlexListener.class,
        onUpdate = CustomFlexListener.class
)
@TableIdPrefix("PDC")
@Data
public class ProductCategory extends BaseEntity implements Serializable, Cloneable {
    /**
     * 商品类别名称
     */
    private String categoryName;

    /**
     * 父类
     */
    private String parentId;

    @Override
    public ProductCategory clone() {
        ProductCategory cloneEntity = null;
        try {
            cloneEntity = (ProductCategory) super.clone();
        } catch (CloneNotSupportedException ignore) {

        }
        return cloneEntity;
    }
}