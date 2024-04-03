package com.lsh.vivo.service.impl;

import com.lsh.vivo.entity.GoodsPicture;
import com.lsh.vivo.entity.GoodsSku;
import com.lsh.vivo.enumerate.*;
import com.lsh.vivo.exception.BaseRequestErrorException;
import com.lsh.vivo.mapper.GoodsSkuMapper;
import com.lsh.vivo.service.GoodsCategoryService;
import com.lsh.vivo.service.GoodsPictureService;
import com.lsh.vivo.service.GoodsSkuService;
import com.lsh.vivo.service.system.impl.CommonServiceImpl;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.If;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.lsh.vivo.entity.table.GoodsSkuTableDef.GOODS_SKU;
import static com.lsh.vivo.entity.table.GoodsTableDef.GOODS;
import static com.mybatisflex.core.query.QueryMethods.*;

/**
 * @author ASUS
 * @description 针对表【goods_sku】的数据库操作Service实现
 * @createDate 2024-01-02 09:53:30
 */
@Service
@RequiredArgsConstructor
public class GoodsSkuServiceImpl extends CommonServiceImpl<GoodsSkuMapper, GoodsSku>
        implements GoodsSkuService {

    private final GoodsPictureService goodsPictureService;
    private final GoodsCategoryService goodsCategoryService;

    @Override
    public Page<GoodsSku> page(Page<GoodsSku> page, String name, GoodsStatusEnum statusEnum, String memory, String color) {
        String status = statusEnum == null ? null : statusEnum.name();
        return queryChain()
                .from(GOODS_SKU)
                .where(GOODS_SKU.GOODS_NAME.eq(name, If::hasText))
                .and(GOODS_SKU.STATUS.eq(status))
                .and(GOODS_SKU.ATTRIBUTE.like(memory, If::hasText).and(GOODS_SKU.ATTRIBUTE.like(color, If::hasText)))
                .orderBy(GOODS_SKU.CREATE_TIME.desc(), GOODS_SKU.ID.desc())
                .page(page);
    }

    @Override
    public Page<GoodsSku> page(Page<GoodsSku> page, String keywords, String categoryId) {
        QueryWrapper queryWrapper = query()
                .from(GOODS_SKU)
                .leftJoin(GOODS)
                .on(GOODS_SKU.GOODS_ID.eq(GOODS.ID))
                .where(GOODS_SKU.GOODS_NAME.like(keywords, If::hasText)
                        .or(GOODS_SKU.ATTRIBUTE.like(keywords, If::hasText))
                        .or(GOODS_SKU.ATTRIBUTE.like(keywords, If::hasText))
                )
                .and(GOODS_SKU.STATUS.eq(GoodsStatusEnum.U.name()))
                .and(GOODS.CATEGORY_ID.eq(categoryId, If::hasText))
                .orderBy(GOODS_SKU.CREATE_TIME.desc(), GOODS_SKU.ID.desc());
        return mapper.paginateWithRelations(page, queryWrapper);
    }

    @Override
    public List<GoodsSku> listByGoodsId(String goodsId) {
        QueryWrapper queryWrapper = query()
                .select(GOODS_SKU.ID, GOODS_SKU.GOODS_ID, GOODS_SKU.ATTRIBUTE)
                .from(GOODS_SKU)
                .where(GOODS_SKU.GOODS_ID.eq(goodsId));
        return mapper.selectListWithRelationsByQuery(queryWrapper);
    }

    @Override
    public String getByAttribute(String color, String memory, String goodsId) {
        return queryChain().select(GOODS_SKU.ID)
                .where(GOODS_SKU.ATTRIBUTE.like(color, If::hasText).and(GOODS_SKU.ATTRIBUTE.like(memory, If::hasText)))
                .and(GOODS_SKU.GOODS_ID.eq(goodsId))
                .and(GOODS_SKU.STATUS.eq(GoodsStatusEnum.U.name()))
                .limit(1)
                .oneAs(String.class);
    }

    @Override
    public List<String> getAllAttribute(String goodsId) {
        return queryChain().select(distinct(GOODS_SKU.ATTRIBUTE))
                .and(GOODS_SKU.GOODS_ID.eq(goodsId))
                .and(GOODS_SKU.STATUS.eq(GoodsStatusEnum.U.name()))
                .listAs(String.class);
    }

    @Override
    public List<GoodsSku> listSelect(GoodsStatusEnum statusEnum) {
        String status = statusEnum == null ? null : statusEnum.name();
        return queryChain()
                .where(GOODS_SKU.STATUS.eq(status, If::hasText))
                .and(GOODS_SKU.STATUS.ne(GoodsStatusEnum.D.name(), StringUtils.isBlank(status)))
                .and(GOODS_SKU.STATUS.ne(CommonStatusEnum.T.name(), StringUtils.isBlank(status)))
                .list();
    }

    @Override
    public List<GoodsSku> listStatistics() {
        QueryWrapper queryWrapper = select(GOODS_SKU.GOODS_NAME, sum(GOODS_SKU.SALES).as("sales"))
                .groupBy(GOODS_SKU.GOODS_NAME);
        return mapper.selectListByQuery(queryWrapper);

    }

    @Override
    public void updateStock(String id, Integer stockChange, StockStatusEnum stockStatus) {
        if (StockStatusEnum.O.equals(stockStatus)) {
            stockChange = -stockChange;
        }
        Integer stock = queryChain().select(GOODS_SKU.STOCK)
                .where(GOODS_SKU.ID.eq(id))
                .oneAs(Integer.class);
        if (stock + stockChange < 0) {
            throw new BaseRequestErrorException(BaseResultCodeEnum.ERROR_STOCK_COUNT);
        }
        boolean updateSuccess = updateChain()
                .where(GOODS_SKU.ID.eq(id))
                .set(GOODS_SKU.STOCK, GOODS_SKU.STOCK.add(stockChange))
                .update();
        if (!updateSuccess) {
            throw new BaseRequestErrorException(BaseResultCodeEnum.ERROR_DATA_CHANGED);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(GoodsSku entity) {
        checkGoodsSku(entity);
        super.save(entity);
        List<GoodsPicture> goodsPictures = entity.getGoodsPictures();
        List<GoodsPicture> list = new ArrayList<>();
        for (GoodsPicture picture : goodsPictures) {
            GoodsPicture goodsPicture = new GoodsPicture();
            goodsPicture.setGoodsSkuId(entity.getId());
            goodsPicture.setUrl(picture.getUrl());
            if (0 == goodsPictures.indexOf(picture)) {
                goodsPicture.setMaster(GoodsPictureEnum.T.name());
            } else {
                goodsPicture.setMaster(GoodsPictureEnum.F.name());
            }
            list.add(goodsPicture);
        }
        goodsPictureService.saveBatch(list);
        return true;
    }

    private void checkGoodsSku(GoodsSku entity) {
        String name = queryChain().select(GOODS_SKU.NAME)
                .from(GOODS_SKU)
                .where(GOODS_SKU.NAME.eq(entity.getGoodsName()))
                .oneAs(String.class);
        if (StringUtils.isNotBlank(name)) {
            throw new BaseRequestErrorException(BaseResultCodeEnum.ERROR_EXISTED_GOODS_SKU);
        }
    }
}




