package com.lsh.vivo.entity;

import com.lsh.vivo.annotation.TableIdPrefix;
import com.lsh.vivo.entity.system.BaseEntity;
import com.lsh.vivo.listener.CustomFlexListener;
import com.mybatisflex.annotation.RelationOneToOne;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 订单商品
 *
 * @author lsh
 */
@EqualsAndHashCode(callSuper = true)
@Table(value = "order_item",
        onInsert = CustomFlexListener.class,
        onUpdate = CustomFlexListener.class
)
@TableIdPrefix("OIM")
@Data
public class OrderItem extends BaseEntity implements Serializable, Cloneable {
    /**
     * 订单号
     */
    private String orderId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * sku id
     */
    private String skuId;

    /**
     * 商品数量
     */
    private Integer count;

    private Double totalPrice;

    /**
     * 商品sku
     */
    @RelationOneToOne(
            selfField = "skuId", targetField = "id"
    )
    private GoodsSku goodsSku;

    @Override
    public OrderItem clone() {
        OrderItem cloneEntity = null;
        try {
            cloneEntity = (OrderItem) super.clone();
        } catch (CloneNotSupportedException ignore) {

        }
        return cloneEntity;
    }
}