package com.lsh.vivo.bean.request.goods.sku;

import com.alibaba.fastjson.JSON;
import com.lsh.vivo.bean.response.system.BaseVO;
import com.lsh.vivo.entity.GoodsPicture;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author ASUS
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsSkuSaveVO extends BaseVO {

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
    private JSON attribute;

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
     * 商品图片
     */
    private List<GoodsPicture> goodsPictures;
}