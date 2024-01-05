package com.lsh.vivo.bean.request.goods;

import com.lsh.vivo.bean.response.system.BaseUpdateVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品修改
 *
 * @author pjw
 * @version 1.0.0
 * @since 2023-10-03 17:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "商品修改", name = "商品修改")
public class GoodsUpdateVO extends BaseUpdateVO {

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    private String name;

    /**
     * 商品类别id
     */
    @Schema(description = "商品类别id")
    private String categoryId;

    /**
     * 商品类别名称
     */
    @Schema(description = "商品类别名称")
    private String category;

    /**
     * 商品描述
     */
    @Schema(description = "商品描述")
    private String description;
}
