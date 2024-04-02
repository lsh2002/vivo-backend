package com.lsh.vivo.service.impl;

import com.lsh.vivo.entity.GoodsSeckill;
import com.lsh.vivo.enumerate.BaseResultCodeEnum;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.enumerate.GoodsStatusEnum;
import com.lsh.vivo.exception.BaseRequestErrorException;
import com.lsh.vivo.mapper.GoodsSeckillMapper;
import com.lsh.vivo.service.GoodsSeckillService;
import com.lsh.vivo.service.system.impl.CommonServiceImpl;
import com.lsh.vivo.util.MapperStructTypeConvert;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.If;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import static com.lsh.vivo.entity.table.GoodsSeckillTableDef.GOODS_SECKILL;
import static com.mybatisflex.core.query.QueryMethods.number;

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

    @Override
    public Page<GoodsSeckill> page(Page<GoodsSeckill> page, String name, GoodsStatusEnum statusEnum, Long startTime, Long endTime) {
        String status = statusEnum == null ? null : statusEnum.name();
        return queryChain()
                .from(GOODS_SECKILL)
                .where(GOODS_SECKILL.SKU_NAME.eq(name, If::hasText))
                .and(GOODS_SECKILL.STATUS.eq(status))
                .and(GOODS_SECKILL.STATUS.ne(CommonStatusEnum.T.name()))
                .and(GOODS_SECKILL.START_TIME.gt(mapperStructTypeConvert.longToLocalDateTime(startTime)))
                .and(GOODS_SECKILL.END_TIME.lt(mapperStructTypeConvert.longToLocalDateTime(endTime)))
                .orderBy(GOODS_SECKILL.CREATE_TIME.desc(), GOODS_SECKILL.ID.desc())
                .page(page);
    }

    @Override
    public boolean save(GoodsSeckill entity) {
        boolean exists = queryChain().select(number(1))
                .where(GOODS_SECKILL.SKU_ID.eq(entity.getSkuId()))
                .limit(1)
                .exists();
        if (exists) {
            throw new BaseRequestErrorException(BaseResultCodeEnum.ERROR_EXISTED_GOODS_SECKILL);
        }
        return super.save(entity);
    }
}




