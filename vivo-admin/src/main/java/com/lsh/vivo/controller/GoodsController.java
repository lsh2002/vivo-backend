package com.lsh.vivo.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.lsh.vivo.bean.dto.goods.GoodsConditionDTO;
import com.lsh.vivo.bean.request.goods.GoodsConditionVO;
import com.lsh.vivo.bean.request.goods.GoodsSaveVO;
import com.lsh.vivo.bean.request.goods.GoodsStatusVO;
import com.lsh.vivo.bean.request.goods.GoodsUpdateVO;
import com.lsh.vivo.bean.response.goods.GoodsSelectVO;
import com.lsh.vivo.bean.response.goods.GoodsVO;
import com.lsh.vivo.bean.response.system.PageVO;
import com.lsh.vivo.entity.Goods;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.enumerate.GoodsStatusEnum;
import com.lsh.vivo.mapper.struct.GoodsMpp;
import com.lsh.vivo.service.GoodsService;
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
 * 商品列表模块控制器
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 0:22
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsService goodsService;

    /**
     * 模块前缀
     */
    private static final String MODULE_PREFIX = "goods:";

    @Operation(summary = "查询某个商品信息", description = "授权限控制，goods:detail - 查看单条记录权限, goods:* - 商品模块全部权限")
    @ApiOperationSupport(order = 1)
    @PreAuthorize("hasAuthority('goods:detail') || hasAuthority('goods:*')")
    @GetMapping("/{id}")
    public GoodsVO getById(@NotNull @Valid @PathVariable("id") String id) {
        Goods goods = goodsService.getById(id);
        return GoodsMpp.INSTANCE.toVO(goods);
    }

    @Operation(summary = "获取模块下当前用户有的权限", description = "获取模块下当前用户有的权限,无授权限制")
    @ApiOperationSupport(order = 5)
    @GetMapping("/per")
    public List<String> getPermissions() {
        return goodsService.listPermissions(MODULE_PREFIX);
    }

    @Operation(summary = "查询商品列表信息", description = "授权限控制，goods:view - 查询商品列表权限, goods:* - 商品模块全部权限")
    @ApiOperationSupport(order = 5)
    @PreAuthorize("hasAuthority('goods:view') || hasAuthority('goods:*')")
    @GetMapping
    public PageVO<GoodsVO> listPageable(@NotNull GoodsConditionVO condition) {
        GoodsConditionDTO goodsConditionDTO = GoodsMpp.INSTANCE.toDTO(condition);
        Page<Goods> page = new Page<>(condition.getPage(), condition.getSize());
        Page<Goods> goodsPage = goodsService.page(page, goodsConditionDTO);
        return GoodsMpp.INSTANCE.toPageVO(goodsPage);
    }

    @Operation(summary = "新建商品", description = "授权限控制，goods:save - 新建商品权限, goods:* - 商品模块全部权限")
    @ApiOperationSupport(order = 10)
    @PreAuthorize("hasAuthority('goods:save') || hasAuthority('goods:*')")
    @PostMapping
    public GoodsVO save(@RequestBody @NotNull @Valid GoodsSaveVO goodsSaveVO) {
        Goods newGoods = GoodsMpp.INSTANCE.toDO(goodsSaveVO);
        newGoods.setStatus(GoodsStatusEnum.D.name());
        goodsService.save(newGoods);
        return GoodsMpp.INSTANCE.toVO(newGoods);
    }

    @Operation(summary = "修改商品名称", description = "授权限控制，goods:update - 修改商品名称权限, goods:* - 商品模块全部权限")
    @ApiOperationSupport(order = 15)
    @PreAuthorize("hasAuthority('goods:update') || hasAuthority('goods:*')")
    @PutMapping
    public GoodsVO updateById(@RequestBody @NotNull @Valid GoodsUpdateVO goodsUpdateVO) {
        Goods newGoods = GoodsMpp.INSTANCE.toDO(goodsUpdateVO);
        goodsService.updateById(newGoods);
        newGoods.setRevision(newGoods.getRevision() + 1);
        return GoodsMpp.INSTANCE.toVO(newGoods);
    }

    @Operation(summary = "修改商品状态", description = "授权限控制，goods:update - 修改商品状态权限, goods:* - 商品模块全部权限")
    @ApiOperationSupport(order = 20)
    @PreAuthorize("hasAuthority('goods:update') || hasAuthority('goods:*')")
    @PutMapping("/status")
    public GoodsVO changeStatus(@RequestBody @NotNull @Valid GoodsStatusVO goodsStatusVO) {
        Goods newGoods = GoodsMpp.INSTANCE.toDO(goodsStatusVO);
        goodsService.updateById(newGoods);
        newGoods.setRevision(newGoods.getRevision() + 1);
        return GoodsMpp.INSTANCE.toVO(newGoods);
    }

    @Operation(summary = "获取商品下拉菜单", description = "授权限控制，goods:view - 查询商品列表权限, goods:* - 商品模块全部权限")
    @ApiOperationSupport(order = 25)
    @PreAuthorize("hasAuthority('goods:view') || hasAuthority('goods:*')")
    @GetMapping("/select")
    public List<GoodsSelectVO> select(CommonStatusEnum status) {
        List<Goods> goodsList = goodsService.selectList(status);
        return GoodsMpp.INSTANCE.toVO(goodsList);
    }

    @DeleteMapping
    public void removeByIds(@RequestBody @NotEmpty(message = "id集不可为空") @Parameter List<String> ids) {
        goodsService.removeByIds(ids);
    }
}
