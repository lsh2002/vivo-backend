package com.lsh.vivo.entity;

import com.lsh.vivo.annotation.TableIdPrefix;
import com.lsh.vivo.entity.system.BaseEntity;
import com.lsh.vivo.listener.CustomFlexListener;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 商品详情图
 *
 * @author ASUS
 */
@EqualsAndHashCode(callSuper = true)
@Table(value = "pdp",
        onInsert = CustomFlexListener.class,
        onUpdate = CustomFlexListener.class
)
@TableIdPrefix("pdp")
@Data
public class ProductPicture extends BaseEntity implements Serializable, Cloneable {
    /**
     * 商品id
     */
    private String productId;

    /**
     * 图片地址
     */
    private String url;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否主图
     */
    private String master;

    @Override
    public ProductPicture clone() {
        ProductPicture cloneEntity = null;
        try {
            cloneEntity = (ProductPicture) super.clone();
        } catch (CloneNotSupportedException ignore) {

        }
        return cloneEntity;
    }
}