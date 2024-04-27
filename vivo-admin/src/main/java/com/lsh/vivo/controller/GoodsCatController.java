package com.lsh.vivo.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.lsh.vivo.bean.request.goods.cat.GoodsCategoryConditionVO;
import com.lsh.vivo.bean.request.goods.cat.GoodsCategorySaveVO;
import com.lsh.vivo.bean.request.goods.cat.GoodsCategoryStatusVO;
import com.lsh.vivo.bean.request.goods.cat.GoodsCategoryUpdateVO;
import com.lsh.vivo.bean.response.goods.cat.GoodsCategorySelectVO;
import com.lsh.vivo.bean.response.goods.cat.GoodsCategoryVO;
import com.lsh.vivo.bean.response.system.PageVO;
import com.lsh.vivo.entity.GoodsCategory;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.enumerate.GoodsCatLevelEnum;
import com.lsh.vivo.mapper.struct.GoodsCategoryMpp;
import com.lsh.vivo.service.GoodsCategoryService;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品类别模块控制器
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 0:22
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/goods/cat")
public class GoodsCatController {

    private final GoodsCategoryService goodsCategoryService;

    /**
     * 模块前缀
     */
    private static final String MODULE_PREFIX = "goods-cate:";

    @Operation(summary = "查询某个商品类别信息", description = "授权限控制，goods-cate:detail - 查看单条记录权限, goods-cate:* - 商品类别模块全部权限")
    @ApiOperationSupport(order = 1)
    @PreAuthorize("hasAuthority('goods-cate:detail') || hasAuthority('goods-cate:*')")
    @GetMapping("/{id}")
    public GoodsCategoryVO getById(@NotNull @Valid @PathVariable("id") String id) {
        GoodsCategory goodsCategory = goodsCategoryService.getById(id);
        return GoodsCategoryMpp.INSTANCE.toVO(goodsCategory);
    }

    @Operation(summary = "获取模块下当前用户有的权限", description = "获取模块下当前用户有的权限,无授权限制")
    @ApiOperationSupport(order = 5)
    @GetMapping("/per")
    public List<String> getPermissions() {
        return goodsCategoryService.listPermissions(MODULE_PREFIX);
    }

    @Operation(summary = "查询商品类别列表信息", description = "授权限控制，goods-cate:view - 查询商品类别列表权限, goods-cate:* - 商品类别模块全部权限")
    @ApiOperationSupport(order = 5)
    @PreAuthorize("hasAuthority('goods-cate:view') || hasAuthority('goods-cate:*')")
    @GetMapping
    public PageVO<GoodsCategoryVO> listPageable(@NotNull GoodsCategoryConditionVO condition) {
        Page<GoodsCategory> page = new Page<>(condition.getPage(), condition.getSize());
        Page<GoodsCategory> goodsCategoryPage = goodsCategoryService.page(page, condition.getName(), condition.getLevel(), condition.getStatus());
        return GoodsCategoryMpp.INSTANCE.toPageVO(goodsCategoryPage);
    }

    @Operation(summary = "新建商品类别", description = "授权限控制，goods-cate:save - 新建商品类别权限, goods-cate:* - 商品类别模块全部权限")
    @ApiOperationSupport(order = 10)
    @PreAuthorize("hasAuthority('goods-cate:save') || hasAuthority('goods-cate:*')")
    @PostMapping
    public GoodsCategoryVO save(@RequestBody @NotNull @Valid GoodsCategorySaveVO goodsCatSaveVO) {
        GoodsCategory newGoodsCategory = GoodsCategoryMpp.INSTANCE.toDO(goodsCatSaveVO);
        goodsCategoryService.save(newGoodsCategory);
        return GoodsCategoryMpp.INSTANCE.toVO(newGoodsCategory);
    }

    @Operation(summary = "修改商品类别名称", description = "授权限控制，goods-cate:update - 修改商品类别名称权限, goods-cate:* - 商品类别模块全部权限")
    @ApiOperationSupport(order = 15)
    @PreAuthorize("hasAuthority('goods-cate:update') || hasAuthority('goods-cate:*')")
    @PutMapping
    public GoodsCategoryVO updateById(@RequestBody @NotNull @Valid GoodsCategoryUpdateVO goodsCatUpdateVO) {
        GoodsCategory newGoodsCategory = GoodsCategoryMpp.INSTANCE.toDO(goodsCatUpdateVO);
        goodsCategoryService.updateById(newGoodsCategory);
        newGoodsCategory.setRevision(newGoodsCategory.getRevision() + 1);
        return GoodsCategoryMpp.INSTANCE.toVO(newGoodsCategory);
    }

    @Operation(summary = "修改商品类别状态", description = "授权限控制，goods-cate:update - 修改商品类别状态权限, goods-cate:* - 商品类别模块全部权限")
    @ApiOperationSupport(order = 20)
    @PreAuthorize("hasAuthority('goods-cate:update') || hasAuthority('goods-cate:*')")
    @PutMapping("/status")
    public GoodsCategoryVO changeStatus(@RequestBody @NotNull @Valid GoodsCategoryStatusVO goodsCatStatusVO) {
        GoodsCategory newGoodsCategory = GoodsCategoryMpp.INSTANCE.toDO(goodsCatStatusVO);
        goodsCategoryService.updateById(newGoodsCategory);
        newGoodsCategory.setRevision(newGoodsCategory.getRevision() + 1);
        return GoodsCategoryMpp.INSTANCE.toVO(newGoodsCategory);
    }

    @Operation(summary = "获取商品类别下拉框数据", description = "不传参数时查询所有状态的商品类别,传I只查询启用状态商品类别，不授权限控制")
    @ApiOperationSupport(order = 35)
    @GetMapping("/select")
    public List<GoodsCategorySelectVO> selectList(CommonStatusEnum status, GoodsCatLevelEnum level) {
        List<GoodsCategory> goodsCats = goodsCategoryService.selectList(status, level);
        return GoodsCategoryMpp.INSTANCE.toSelectVO(goodsCats);
    }

    @DeleteMapping
    public void removeByIds(@RequestBody @NotEmpty(message = "id集不可为空") @Parameter List<String> ids) {
        goodsCategoryService.removeByIds(ids);
    }
}
