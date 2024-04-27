package com.lsh.vivo.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.lsh.vivo.bean.request.goods.seckill.GoodsSeckillSaveVO;
import com.lsh.vivo.bean.request.goods.seckill.GoodsSeckillSearchVO;
import com.lsh.vivo.bean.response.goods.seckill.GoodsSeckillVO;
import com.lsh.vivo.bean.response.system.PageVO;
import com.lsh.vivo.entity.GoodsSeckill;
import com.lsh.vivo.mapper.struct.GoodsSeckillMpp;
import com.lsh.vivo.service.GoodsSeckillService;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lsh
 * @since 2023-08-28 19:58
 */
@RestController
@RequestMapping("/goods/seckill")
@RequiredArgsConstructor
public class GoodsSeckillController {

    private final GoodsSeckillService goodsSeckillService;

    /**
     * 模块前缀
     */
    private static final String MODULE_PREFIX = "goods-seckill:";

    @ApiOperationSupport(order = 5)
    @GetMapping("/per")
    public List<String> getPermissions() {
        return goodsSeckillService.listPermissions(MODULE_PREFIX);
    }

    @GetMapping
    public PageVO<GoodsSeckillVO> listPageable(@NotNull GoodsSeckillSearchVO condition) {
        Page<GoodsSeckill> page = new Page<>(condition.getPage(), condition.getSize());
        Page<GoodsSeckill> goodsSeckillPage = goodsSeckillService.page(page, condition.getName(), condition.getStatus(), condition.getStartTime(), condition.getEndTime());
        return GoodsSeckillMpp.INSTANCE.toPageVO(goodsSeckillPage);
    }

    @PostMapping
    public GoodsSeckillVO save(@NotNull @RequestBody GoodsSeckillSaveVO saveVO) {
        GoodsSeckill goodsSeckill = GoodsSeckillMpp.INSTANCE.toDO(saveVO);
        goodsSeckillService.save(goodsSeckill);
        return GoodsSeckillMpp.INSTANCE.toVO(goodsSeckill);
    }

    @DeleteMapping
    public void removeByIds(@RequestBody @NotEmpty(message = "id集不可为空") @Parameter List<String> ids) {
        goodsSeckillService.removeByIds(ids);
    }
}
