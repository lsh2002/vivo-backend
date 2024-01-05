package com.lsh.vivo.bean.dto.goods;

import com.lsh.vivo.bean.request.system.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品查询条件
 *
 * @author pjw
 * @version 1.0.0
 * @since 2023-10-03 17:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsConditionDTO extends PageRequest {

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品类别id
     */
    private String categoryId;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 启用状态，I:有效，H:无效
     */
    private String status;
}
