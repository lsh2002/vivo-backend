package com.lsh.vivo.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.lsh.vivo.bean.request.goods.sku.GoodsSkuSearchVO;
import com.lsh.vivo.bean.response.goods.GoodsSkuVO;
import com.lsh.vivo.bean.response.system.PageVO;
import com.lsh.vivo.entity.GoodsSku;
import com.lsh.vivo.mapper.struct.GoodsSkuMpp;
import com.lsh.vivo.service.GoodsSkuService;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lsh
 * @since 2023-08-19 13:26
 */
@RestController
@RequestMapping("/goods/sku")
@RequiredArgsConstructor
public class GoodsSkuController {

    private final GoodsSkuService goodsSkuService;

    @Operation(summary = "查询某个商品规格")
    @ApiOperationSupport(order = 1)
    @GetMapping("/{id}")
    public GoodsSkuVO getById(@NotNull @Valid @PathVariable("id") String id) {
        GoodsSku goodsSku = goodsSkuService.getMapper().selectOneWithRelationsById(id);
        return GoodsSkuMpp.INSTANCE.toVO(goodsSku);
    }

    @Operation(summary = "根据商品id查询商品sku")
    @ApiOperationSupport(order = 5)
    @GetMapping("/all")
    public List<GoodsSkuVO> listByGoodsId(@NotNull @Valid String goodsId) {
        List<GoodsSku> goodsSkus = goodsSkuService.listByGoodsId(goodsId);
        return GoodsSkuMpp.INSTANCE.toVO(goodsSkus);
    }

    @ApiOperationSupport(order = 10)
    @GetMapping
    public PageVO<GoodsSkuVO> listPageable(@NotNull GoodsSkuSearchVO condition) {
        Page<GoodsSku> page = new Page<>(condition.getPage(), condition.getSize());
        Page<GoodsSku> goodsSkuPage = goodsSkuService.page(page, condition.getKeywords(), condition.getCategoryId());
        return GoodsSkuMpp.INSTANCE.toPageVO(goodsSkuPage);
    }

    @Operation(summary = "查询某个商品规格")
    @ApiOperationSupport(order = 15)
    @GetMapping("/byAttr")
    public String getByAttribute(@NotNull @Valid String color, @NotNull @Valid String memory, @NotNull @Valid String goodsId) {
        return goodsSkuService.getByAttribute(color, memory, goodsId);
    }

    @ApiOperationSupport(order = 15)
    @GetMapping("/allAttr")
    public List<String> getAllAttribute(@NotNull @Valid String goodsId) {
        return goodsSkuService.getAllAttribute(goodsId);
    }

}
