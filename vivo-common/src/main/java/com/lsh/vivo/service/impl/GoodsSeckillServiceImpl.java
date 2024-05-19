package com.lsh.vivo.service.impl;

import com.lsh.vivo.entity.GoodsSeckill;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.enumerate.GoodsStatusEnum;
import com.lsh.vivo.mapper.GoodsSeckillMapper;
import com.lsh.vivo.service.GoodsSeckillService;
import com.lsh.vivo.service.GoodsSkuService;
import com.lsh.vivo.service.system.impl.CommonServiceImpl;
import com.lsh.vivo.util.MapperStructTypeConvert;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.If;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.lsh.vivo.entity.table.GoodsSeckillTableDef.GOODS_SECKILL;
import static com.lsh.vivo.entity.table.GoodsSkuTableDef.GOODS_SKU;
import static com.mybatisflex.core.query.QueryMethods.select;

/**
 * @author ASUS
 * @description 针对表【goods_seckill(商品秒杀)】的数据库操作Service实现
 * @createLocalDateTime 2023-10-28 22:51:58
 */
@Service
public class GoodsSeckillServiceImpl extends CommonServiceImpl<GoodsSeckillMapper, GoodsSeckill>
        implements GoodsSeckillService {

    @Resource
    private MapperStructTypeConvert mapperStructTypeConvert;
    @Resource
    private GoodsSkuService goodsSkuService;

    @Override
    public Page<GoodsSeckill> page(Page<GoodsSeckill> page, String name, GoodsStatusEnum statusEnum, Long startTime, Long endTime) {
        String status = statusEnum == null ? null : statusEnum.name();
        QueryWrapper queryWrapper =  select()
                .from(GOODS_SECKILL)
                .where(GOODS_SECKILL.SKU_NAME.eq(name, If::hasText))
                .and(GOODS_SECKILL.STATUS.eq(status))
                .and(GOODS_SECKILL.STATUS.ne(CommonStatusEnum.T.name()))
                .and(GOODS_SECKILL.START_TIME.gt(mapperStructTypeConvert.longToLocalDateTime(startTime)))
                .and(GOODS_SECKILL.END_TIME.lt(mapperStructTypeConvert.longToLocalDateTime(endTime)))
                .orderBy(GOODS_SECKILL.CREATE_TIME.desc(), GOODS_SECKILL.ID.desc());
        return mapper.paginateWithRelations(page, queryWrapper);
    }

    @Override
    public List<String> listFinished() {
        QueryWrapper queryWrapper = select(GOODS_SECKILL.SKU_ID)
                .where(GOODS_SECKILL.END_TIME.lt(LocalDateTime.now()));
        return mapper.selectListByQueryAs(queryWrapper, String.class);
    }

    @Override
    public Page<GoodsSeckill> page(Page<GoodsSeckill> page) {
        QueryWrapper queryWrapper =  select()
                .from(GOODS_SECKILL)
                .where(GOODS_SECKILL.STATUS.eq(GoodsStatusEnum.U.name()))
                .and(GOODS_SECKILL.START_TIME.le(LocalDateTime.now()))
                .and(GOODS_SECKILL.END_TIME.gt(LocalDateTime.now()))
                .leftJoin(GOODS_SKU).on(GOODS_SECKILL.SKU_ID.eq(GOODS_SKU.ID))
                .where(GOODS_SKU.STOCK.ge(GOODS_SECKILL.SECKILL_NUM))
                .orderBy(GOODS_SECKILL.CREATE_TIME.desc(), GOODS_SECKILL.ID.desc());
        return mapper.paginateWithRelations(page, queryWrapper);
    }
}




