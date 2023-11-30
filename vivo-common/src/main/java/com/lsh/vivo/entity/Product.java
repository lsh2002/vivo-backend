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
 * @author ASUS
 */
@EqualsAndHashCode(callSuper = true)
@Table(value = "product",
        onInsert = CustomFlexListener.class,
        onUpdate = CustomFlexListener.class
)
@TableIdPrefix("PDT")
@Data
public class Product extends BaseEntity implements Serializable, Cloneable {
    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品类别id
     */
    private Integer categoryId;

    /**
     * 商品版本
     */
    private String verision;

    /**
     * 颜色
     */
    private String color;

    /**
     * 介绍
     */
    private String productIntro;

    /**
     * 商品图id
     */
    private String productPictureId;

    /**
     * 定价
     */
    private Double productPrice;

    /**
     * 售价
     */
    private Double productSellingPrice;

    /**
     * 库存数
     */
    private Integer productNum;

    /**
     * 售出数
     */
    private Integer productSales;

    @Override
    public Product clone() {
        Product cloneEntity = null;
        try {
            cloneEntity = (Product) super.clone();
        } catch (CloneNotSupportedException ignore) {

        }
        return cloneEntity;
    }
}