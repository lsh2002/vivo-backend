package com.lsh.vivo.bean.request.goods.cat;

import com.lsh.vivo.enumerate.GoodsCatLevelEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author lsh
 * @version 1.0.0
 * @since 2024-01-02 11:25
 */
@Data
public class GoodsCategorySaveVO {

    /**
     * 商品类别名称
     */
    @Schema(description = "商品类别名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "商品类别名称不能为空！")
    private String name;

    /**
     * 类目级别
     */
    @NotNull(message = "类目级别不能为空！")
    private GoodsCatLevelEnum level;

    /**
     * 父类id
     */
    @Schema(description = "父类id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String parentId;

    /**
     * 父类名称
     */
    @Schema(description = "父类名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String parentName;
}
