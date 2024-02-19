package com.lsh.vivo.service.impl;

import com.lsh.vivo.entity.GoodsSku;
import com.lsh.vivo.enumerate.GoodsStatusEnum;
import com.lsh.vivo.mapper.GoodsSkuMapper;
import com.lsh.vivo.service.GoodsSkuService;
import com.lsh.vivo.service.system.impl.CommonServiceImpl;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.If;
import org.springframework.stereotype.Service;

import static com.lsh.vivo.entity.table.GoodsSkuTableDef.GOODS_SKU;

/**
 * @author ASUS
 * @description 针对表【goods_sku】的数据库操作Service实现
 * @createDate 2024-01-02 09:53:30
 */
@Service
public class GoodsSkuServiceImpl extends CommonServiceImpl<GoodsSkuMapper, GoodsSku>
        implements GoodsSkuService {

    @Override
    public Page<GoodsSku> page(Page<GoodsSku> page, String name, GoodsStatusEnum statusEnum) {
        String status = statusEnum == null ? null : statusEnum.name();
        return queryChain()
                .from(GOODS_SKU)
                .where(GOODS_SKU.GOODS_NAME.eq(name, If::hasText))
                .and(GOODS_SKU.STATUS.eq(status))
                .orderBy(GOODS_SKU.CREATE_TIME.desc(), GOODS_SKU.ID.desc())
                .page(page);
    }
}




