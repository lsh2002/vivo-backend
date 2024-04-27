package com.lsh.vivo.controller;

import com.lsh.vivo.bean.request.system.PageRequest;
import com.lsh.vivo.bean.response.goods.seckill.GoodsSeckillVO;
import com.lsh.vivo.bean.response.system.PageVO;
import com.lsh.vivo.entity.GoodsSeckill;
import com.lsh.vivo.mapper.struct.GoodsSeckillMpp;
import com.lsh.vivo.service.GoodsSeckillService;
import com.mybatisflex.core.paginate.Page;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lsh
 * @since 2023-08-28 19:58
 */
@RestController
@RequestMapping("/goods/seckill")
@RequiredArgsConstructor
public class GoodsSeckillController {

    private final GoodsSeckillService goodsSeckillService;

    @GetMapping
    public PageVO<GoodsSeckillVO> listPageable(@NotNull PageRequest request) {
        Page<GoodsSeckill> page = new Page<>(request.getPage(), request.getSize());
        Page<GoodsSeckill> goodsSeckillPage = goodsSeckillService.page(page);
        return GoodsSeckillMpp.INSTANCE.toPageVO(goodsSeckillPage);
    }
}
