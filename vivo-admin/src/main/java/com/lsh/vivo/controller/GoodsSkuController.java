package com.lsh.vivo.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.lsh.vivo.bean.request.goods.sku.*;
import com.lsh.vivo.bean.response.goods.GoodsSkuVO;
import com.lsh.vivo.bean.response.system.PageVO;
import com.lsh.vivo.entity.GoodsSku;
import com.lsh.vivo.enumerate.GoodsStatusEnum;
import com.lsh.vivo.mapper.struct.GoodsSkuMpp;
import com.lsh.vivo.service.GoodsSkuService;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品Sku模块控制器
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 0:22
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/goods/sku")
public class GoodsSkuController {

    private final GoodsSkuService goodsSkuService;

    /**
     * 模块前缀
     */
    private static final String MODULE_PREFIX = "goods-sku:";

    @Operation(summary = "查询某个商品规格", description = "授权限控制，goods-sku:detail - 查看单条记录权限, goods-sku:* - 商品模块全部权限")
    @ApiOperationSupport(order = 1)
    @PreAuthorize("hasAuthority('goods-sku:detail') || hasAuthority('goods-sku:*')")
    @GetMapping("/{id}")
    public GoodsSkuVO getById(@NotNull @Valid @PathVariable("id") String id) {
        GoodsSku goodsSku = goodsSkuService.getById(id);
        return GoodsSkuMpp.INSTANCE.toVO(goodsSku);
    }

    @Operation(summary = "获取模块下当前用户有的权限", description = "获取模块下当前用户有的权限,无授权限制")
    @ApiOperationSupport(order = 5)
    @GetMapping("/per")
    public List<String> getPermissions() {
        return goodsSkuService.listPermissions(MODULE_PREFIX);
    }

    @Operation(summary = "查询商品规格信息", description = "授权限控制，goods-sku:view - 查询商品规格权限, goods-sku:* - 商品模块全部权限")
    @ApiOperationSupport(order = 5)
    @PreAuthorize("hasAuthority('goods-sku:view') || hasAuthority('goods-sku:*')")
    @GetMapping
    public PageVO<GoodsSkuVO> listPageable(@NotNull GoodsSkuConditionVO condition) {
        Page<GoodsSku> page = new Page<>(condition.getPage(), condition.getSize());
        Page<GoodsSku> goodsSkuPage = goodsSkuService.page(page, condition.getName(), condition.getStatus(), condition.getMemory(), condition.getColor());
        return GoodsSkuMpp.INSTANCE.toPageVO(goodsSkuPage);
    }

    @Operation(summary = "新建商品规格", description = "授权限控制，goods-sku:save - 新建商品规格权限, goods-sku:* - 商品规格模块全部权限")
    @ApiOperationSupport(order = 10)
    @PreAuthorize("hasAuthority('goods-sku:save') || hasAuthority('goods-sku:*')")
    @PostMapping
    public GoodsSkuVO save(@RequestBody @NotNull @Valid GoodsSkuSaveVO goodsSkuSaveVO) {
        GoodsSku newGoodsSku = GoodsSkuMpp.INSTANCE.toDO(goodsSkuSaveVO);
        newGoodsSku.setStock(0);
        newGoodsSku.setSales(0);
        newGoodsSku.setStatus(GoodsStatusEnum.D.name());
        goodsSkuService.save(newGoodsSku);
        return GoodsSkuMpp.INSTANCE.toVO(newGoodsSku);
    }

    @Operation(summary = "修改商品规格", description = "授权限控制，goods-sku:update - 修改商品规格权限, goods-sku:* - 商品规格模块全部权限")
    @ApiOperationSupport(order = 15)
    @PreAuthorize("hasAuthority('goods-sku:update') || hasAuthority('goods-sku:*')")
    @PutMapping
    public GoodsSkuVO updateById(@RequestBody @NotNull @Valid GoodsSkuUpdateVO goodsSkuUpdateVO) {
        GoodsSku newGoodsSku = GoodsSkuMpp.INSTANCE.toDO(goodsSkuUpdateVO);
        goodsSkuService.updateById(newGoodsSku);
        newGoodsSku.setRevision(newGoodsSku.getRevision() + 1);
        return GoodsSkuMpp.INSTANCE.toVO(newGoodsSku);
    }

    @Operation(summary = "修改商品规格状态", description = "授权限控制，goods-sku:update - 修改商品规格状态权限, goods-sku:* - 商品规格模块全部权限")
    @ApiOperationSupport(order = 20)
    @PreAuthorize("hasAuthority('goods-sku:update') || hasAuthority('goods-sku:*')")
    @PutMapping("/status")
    public GoodsSkuVO changeStatus(@RequestBody @NotNull @Valid GoodsSkuStatusVO goodsSkuStatusVO) {
        GoodsSku newGoodsSku = GoodsSkuMpp.INSTANCE.toDO(goodsSkuStatusVO);
        goodsSkuService.updateById(newGoodsSku);
        newGoodsSku.setRevision(newGoodsSku.getRevision() + 1);
        return GoodsSkuMpp.INSTANCE.toVO(newGoodsSku);
    }

    @Operation(summary = "修改商品规格状态", description = "授权限控制，goods-sku:update - 修改商品规格状态权限, goods-sku:* - 商品规格模块全部权限")
    @ApiOperationSupport(order = 25)
    @PreAuthorize("hasAuthority('goods-sku:update') || hasAuthority('goods-sku:*')")
    @PutMapping("/stock")
    public GoodsSkuVO changeStatus(@RequestBody @NotNull @Valid StockUpdateVO stockUpdateVO) {
        GoodsSku newGoodsSku = GoodsSkuMpp.INSTANCE.toDO(stockUpdateVO);
        goodsSkuService.updateStock(stockUpdateVO.getId(), stockUpdateVO.getStockChange(), stockUpdateVO.getStockStatus());
        newGoodsSku.setRevision(newGoodsSku.getRevision() + 1);
        return GoodsSkuMpp.INSTANCE.toVO(newGoodsSku);
    }

    @GetMapping("/select")
    public List<GoodsSkuVO> listSelect(@NotNull GoodsStatusEnum status) {
        List<GoodsSku> goodsSkus = goodsSkuService.listSelect(status);
        return GoodsSkuMpp.INSTANCE.toVO(goodsSkus);
    }

    @GetMapping("/statistics")
    public List<GoodsSkuVO> listStatistics() {
        List<GoodsSku> goodsSkus = goodsSkuService.listStatistics();
        return GoodsSkuMpp.INSTANCE.toVO(goodsSkus);
    }
}
