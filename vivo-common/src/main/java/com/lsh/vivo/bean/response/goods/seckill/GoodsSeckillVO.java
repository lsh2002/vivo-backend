package com.lsh.vivo.bean.response.goods.seckill;

import com.lsh.vivo.bean.response.goods.GoodsSkuVO;
import com.lsh.vivo.bean.response.system.BaseEntityVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品秒杀
 *
 * @author ASUS
 * @TableName goods_seckill
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsSeckillVO extends BaseEntityVO {

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
    private Long startTime;

    /**
     * 结束时间
     */
    private Long endTime;

    private GoodsSkuVO goodsSku;
}