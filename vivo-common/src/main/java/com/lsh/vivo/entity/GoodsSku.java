package com.lsh.vivo.entity;

import com.lsh.vivo.annotation.TableIdPrefix;
import com.lsh.vivo.entity.system.BaseEntity;
import com.lsh.vivo.listener.CustomFlexListener;
import com.mybatisflex.annotation.RelationManyToOne;
import com.mybatisflex.annotation.RelationOneToMany;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author ASUS
 * @TableName goods_sku
 */
@EqualsAndHashCode(callSuper = true)
@Table(value = "goods_sku",
        onInsert = CustomFlexListener.class,
        onUpdate = CustomFlexListener.class
)
@TableIdPrefix("GSk")
@Data
public class GoodsSku extends BaseEntity implements Serializable {

    /**
     * 商品Sku名称
     */
    private String name;

    /**
     * 商品id
     */
    private String goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 属性
     */
    private String attribute;

    /**
     * 定价
     */
    private Double price;

    /**
     * 售价
     */
    private Double sellingPrice;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 销售量
     */
    private Integer sales;

    /**
     * 备注
     */
    private String remark;

    /**
     * 商品
     */
    @RelationManyToOne(
            selfField = "goodsId", targetField = "id",
            joinTargetColumn = "id"
    )
    private Goods goods;

    /**
     * 商品图片
     */
    @RelationOneToMany(
            selfField = "id", targetField = "goodsSkuId",
            selectColumns = {"url"}
    )
    private List<GoodsPicture> goodsPictures;

    @Override
    public GoodsSku clone() {
        GoodsSku cloneEntity = null;
        try {
            cloneEntity = (GoodsSku) super.clone();
        } catch (CloneNotSupportedException ignore) {

        }
        return cloneEntity;
    }
}