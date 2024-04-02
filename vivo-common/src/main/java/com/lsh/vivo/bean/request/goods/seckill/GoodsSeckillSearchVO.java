package com.lsh.vivo.bean.request.goods.seckill;

import com.lsh.vivo.bean.request.system.PageRequest;
import com.lsh.vivo.enumerate.GoodsStatusEnum;
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
public class GoodsSeckillSearchVO extends PageRequest {

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    private String name;

    private Long startTime;

    private Long endTime;

    private GoodsStatusEnum status;
}
