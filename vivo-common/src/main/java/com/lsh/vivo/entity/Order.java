package com.lsh.vivo.entity;

import com.lsh.vivo.annotation.TableIdPrefix;
import com.lsh.vivo.entity.system.BaseEntity;
import com.lsh.vivo.listener.CustomFlexListener;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.RelationOneToMany;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单
 *
 * @author lsh
 */
@EqualsAndHashCode(callSuper = true)
@Table(value = "order",
        onInsert = CustomFlexListener.class,
        onUpdate = CustomFlexListener.class
)
@TableIdPrefix("ORD")
@Data
public class Order extends BaseEntity implements Serializable, Cloneable {
    /**
     * 订单号
     */
    private String orderId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 服务类型
     */
    private String serviceType;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 收货人地址
     */
    private String receiverAddress;

    /**
     * 商品单价
     */
    private Double totalPrice;

    /**
     * 下单时间
     */
    private LocalDateTime orderTime;

    private LocalDateTime serviceTime;

    /**
     * 付款时间
     */
    private LocalDateTime payTime;

    /**
     * 发货时间
     */
    private LocalDateTime deliverTime;

    /**
     * 完成时间
     */
    private LocalDateTime finishTime;

    /**
     * 取消时间
     */
    private LocalDateTime cancelTime;

    /**
     * 快递单号
     */
    private String courierNumber;

    private String requestNo;

    @Column(ignore = true)
    private Boolean cart;

    /**
     * 商品
     */
    @RelationOneToMany(
            selfField = "id", targetField = "orderId"
    )
    private List<OrderItem> orderItems;

    @Column(ignore = true)
    private String seckillId;

    @Override
    public Order clone() {
        Order cloneEntity = null;
        try {
            cloneEntity = (Order) super.clone();
        } catch (CloneNotSupportedException ignore) {

        }
        return cloneEntity;
    }
}