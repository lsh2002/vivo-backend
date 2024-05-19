package com.lsh.vivo.bean.response.goods;

import com.lsh.vivo.bean.response.system.BaseUpdateVO;
import com.lsh.vivo.enumerate.GoodsStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品
 *
 * @author ASUS
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsVO extends BaseUpdateVO {

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品类别id
     */
    private String categoryId;

    /**
     * 商品类别
     */
    private String category;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private GoodsStatusEnum status;
}