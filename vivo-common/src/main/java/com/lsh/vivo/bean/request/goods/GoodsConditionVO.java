package com.lsh.vivo.bean.request.goods;

import com.lsh.vivo.bean.request.system.PageRequest;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "商品查询条件", name = "商品查询条件")
public class GoodsConditionVO extends PageRequest {

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
     * 商品描述
     */
    @Schema(description = "商品描述")
    private String description;

    /**
     * 启用状态，I:有效，H:无效
     */
    private CommonStatusEnum status;
}
