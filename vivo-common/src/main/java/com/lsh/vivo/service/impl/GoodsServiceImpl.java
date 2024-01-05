package com.lsh.vivo.service.impl;

import com.lsh.vivo.bean.dto.goods.GoodsConditionDTO;
import com.lsh.vivo.entity.Goods;
import com.lsh.vivo.enumerate.BaseResultCodeEnum;
import com.lsh.vivo.exception.BaseRequestErrorException;
import com.lsh.vivo.mapper.GoodsMapper;
import com.lsh.vivo.service.GoodsService;
import com.lsh.vivo.service.system.impl.CommonServiceImpl;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.If;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import static com.lsh.vivo.entity.table.GoodsTableDef.GOODS;
import static com.mybatisflex.core.query.QueryMethods.number;

/**
 * @author ASUS
 * @description 针对表【goods(商品)】的数据库操作Service实现
 * @createLocalDateTime 2023-10-28 22:51:50
 */
@Service
public class GoodsServiceImpl extends CommonServiceImpl<GoodsMapper, Goods>
        implements GoodsService {

    @Override
    public Page<Goods> page(Page<Goods> page, GoodsConditionDTO goodsConditionDTO) {
        return queryChain().select()
                .where(GOODS.NAME.like(goodsConditionDTO.getName(), If::hasText))
                .and(GOODS.CATEGORY_ID.eq(goodsConditionDTO.getCategoryId(), If::hasText))
                .and(GOODS.DESCRIPTION.like(goodsConditionDTO.getDescription(), If::hasText))
                .and(GOODS.STATUS.eq(goodsConditionDTO.getStatus(), If::hasText))
                .orderBy(GOODS.CREATE_TIME.desc(), GOODS.ID.desc())
                .page(page);
    }

    @Override
    public boolean save(Goods entity) {
        checkGoods(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(Goods entity) {
        boolean update = StringUtils.isNotBlank(entity.getName());
        if (update) {
            checkGoods(entity);
        }
        return super.updateById(entity);
    }

    /**
     * 校验商品信息
     */
    private void checkGoods(Goods entity) {
        String queryGoods = queryChain().select(number(1))
                .from(GOODS)
                .where(GOODS.NAME.eq(entity.getName()))
                .and(GOODS.ID.ne(entity.getId(), If::hasText))
                .limit(1).oneAs(String.class);
        if (existByCondition(queryGoods)) {
            throw new BaseRequestErrorException(BaseResultCodeEnum.ERROR_GOODS_EXIST);
        }
    }
}




