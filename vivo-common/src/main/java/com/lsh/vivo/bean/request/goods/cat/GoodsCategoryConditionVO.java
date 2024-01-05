package com.lsh.vivo.bean.request.goods.cat;

import com.lsh.vivo.bean.request.system.PageRequest;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.enumerate.GoodsCatLevelEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品类别查询条件
 *
 * @author pjw
 * @version 1.0.0
 * @since 2023-10-03 17:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "商品类别查询条件", name = "商品类别查询条件")
public class GoodsCategoryConditionVO extends PageRequest {

    /**
     * 商品类别名称
     */
    @Schema(description = "商品类别名称")
    private String name;

    /**
     * 类目级别
     */
    @Schema(description = "类目级别")
    private GoodsCatLevelEnum level;

    /**
     * 启用状态，I:有效，H:无效
     */
    private CommonStatusEnum status;
}
