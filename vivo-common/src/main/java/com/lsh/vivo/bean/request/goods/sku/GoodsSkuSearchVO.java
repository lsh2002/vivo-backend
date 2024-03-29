package com.lsh.vivo.bean.request.goods.sku;

import com.lsh.vivo.bean.request.system.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品sku查询条件
 *
 * @author pjw
 * @version 1.0.0
 * @since 2023-10-03 17:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "商品sku查询条件", name = "商品sku查询条件")
public class GoodsSkuSearchVO extends PageRequest {

    /**
     * 查询关键词
     */
    private String keywords;

    private String categoryId;
}
