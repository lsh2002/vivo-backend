package com.lsh.vivo.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.lsh.vivo.bean.response.goods.cat.GoodsCategorySelectVO;
import com.lsh.vivo.bean.response.goods.cat.GoodsCategoryVO;
import com.lsh.vivo.entity.GoodsCategory;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.enumerate.GoodsCatLevelEnum;
import com.lsh.vivo.mapper.struct.GoodsCategoryMpp;
import com.lsh.vivo.service.GoodsCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperationSupport(order = 1)
    @GetMapping("/{id}")
    public GoodsCategoryVO getById(@NotNull @Valid @PathVariable("id") String id) {
        GoodsCategory goodsCategory = goodsCategoryService.getById(id);
        return GoodsCategoryMpp.INSTANCE.toVO(goodsCategory);
    }

    @Operation(summary = "获取商品类别数据", description = "不传参数时查询所有状态的商品类别，不授权限控制")
    @ApiOperationSupport(order = 10)
    @GetMapping
    public List<GoodsCategorySelectVO> selectList(CommonStatusEnum status, GoodsCatLevelEnum level) {
        List<GoodsCategory> goodsCats = goodsCategoryService.selectList(status, level);
        return GoodsCategoryMpp.INSTANCE.toSelectVO(goodsCats);
    }

    @GetMapping("/child")
    public List<GoodsCategorySelectVO> selectList(String id) {
        List<GoodsCategory> goodsCats = goodsCategoryService.childList(id);
        return GoodsCategoryMpp.INSTANCE.toSelectVO(goodsCats);
    }
}
