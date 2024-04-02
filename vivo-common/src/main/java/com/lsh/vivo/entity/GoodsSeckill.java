package com.lsh.vivo.entity;

import com.lsh.vivo.annotation.TableIdPrefix;
import com.lsh.vivo.entity.system.BaseEntity;
import com.lsh.vivo.listener.CustomFlexListener;
import com.mybatisflex.annotation.RelationOneToOne;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品秒杀
 *
 * @author ASUS
 * @TableName goods_seckill
 */
@EqualsAndHashCode(callSuper = true)
@TableIdPrefix("GDS")
@Table(value = "goods_seckill",
        onInsert = CustomFlexListener.class,
        onUpdate = CustomFlexListener.class)
@Data
public class GoodsSeckill extends BaseEntity implements Serializable, Cloneable {

    /**
     * 商品id
     */
    private String skuId;
    private String skuName;

    /**
     * 秒杀价
     */
    private Double seckillPrice;

    /**
     * 数量
     */
    private Integer seckillNum;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;


    /**
     * 商品
     */
    @RelationOneToOne(
            selfField = "skuId", targetField = "id",
            joinTargetColumn = "id"
    )
    private GoodsSku goodsSku;

    @Override
    public GoodsSeckill clone() {
        GoodsSeckill cloneEntity = null;
        try {
            cloneEntity = (GoodsSeckill) super.clone();
        } catch (CloneNotSupportedException ignore) {

        }
        return cloneEntity;
    }
}