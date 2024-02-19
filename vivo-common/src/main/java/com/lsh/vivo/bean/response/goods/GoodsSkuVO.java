package com.lsh.vivo.bean.response.goods;

import com.alibaba.fastjson.JSON;
import com.lsh.vivo.bean.response.system.BaseEntityVO;
import com.lsh.vivo.enumerate.GoodsStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品SKU信息
 *
 * @author ASUS
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "商品SKU信息")
public class GoodsSkuVO extends BaseEntityVO {

    /**
     * 商品id
     */
    @Schema(name = "商品id", description = "商品id")
    private String goodsId;

    /**
     * 商品名称
     */
    @Schema(name = "商品名称", description = "商品名称")
    private String goodsName;

    /**
     * 属性
     */
    @Schema(name = "属性", description = "属性")
    private JSON attribute;

    /**
     * 定价
     */
    @Schema(name = "定价", description = "定价")
    private Double price;

    /**
     * 售价
     */
    @Schema(name = "售价", description = "售价")
    private Double sellingPrice;

    /**
     * 库存
     */
    @Schema(name = "库存", description = "库存")
    private Integer stock;

    /**
     * 销售量
     */
    @Schema(name = "销售量", description = "销售量")
    private Integer sales;

    /**
     * 备注
     */
    @Schema(name = "备注", description = "备注")
    private String remark;

    /**
     * 商品状态
     */
    private GoodsStatusEnum status;
}