package com.lsh.vivo.entity;

import com.lsh.vivo.annotation.TableIdPrefix;
import com.lsh.vivo.entity.system.BaseEntity;
import com.lsh.vivo.listener.CustomFlexListener;
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
@Table(value = "order_seckill",
        onInsert = CustomFlexListener.class,
        onUpdate = CustomFlexListener.class
)
@TableIdPrefix("OSL")
@Data
public class OrderSeckill extends BaseEntity implements Serializable, Cloneable {
    /**
     * 订单号
     */
    private String orderId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * seckillId
     */
    private String seckillId;

    @Override
    public OrderSeckill clone() {
        OrderSeckill cloneEntity = null;
        try {
            cloneEntity = (OrderSeckill) super.clone();
        } catch (CloneNotSupportedException ignore) {

        }
        return cloneEntity;
    }
}