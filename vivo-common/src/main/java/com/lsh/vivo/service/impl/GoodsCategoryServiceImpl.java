package com.lsh.vivo.service.impl;

import com.lsh.vivo.entity.GoodsCategory;
import com.lsh.vivo.enumerate.BaseResultCodeEnum;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.enumerate.GoodsCatLevelEnum;
import com.lsh.vivo.exception.BaseRequestErrorException;
import com.lsh.vivo.mapper.GoodsCategoryMapper;
import com.lsh.vivo.service.GoodsCategoryService;
import com.lsh.vivo.service.system.impl.CommonServiceImpl;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.If;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lsh.vivo.entity.table.GoodsCategoryTableDef.GOODS_CATEGORY;
import static com.mybatisflex.core.query.QueryMethods.number;


/**
 * @author ASUS
 * @description 针对表【goods_category(商品类别)】的数据库操作Service实现
 * @since 2023-10-28 22:51:52
 */
@Service
public class GoodsCategoryServiceImpl extends CommonServiceImpl<GoodsCategoryMapper, GoodsCategory>
        implements GoodsCategoryService {

    @Override
    public Page<GoodsCategory> page(Page<GoodsCategory> page, String name, GoodsCatLevelEnum levelEnum, CommonStatusEnum statusEnum) {
        String level = levelEnum == null ? null : levelEnum.name();
        String status = statusEnum == null ? null : statusEnum.name();
        return queryChain()
                .where(GOODS_CATEGORY.NAME.like(name))
                .and(GOODS_CATEGORY.LEVEL.eq(level))
                .and(GOODS_CATEGORY.STATUS.eq(status))
                .orderBy(GOODS_CATEGORY.CREATE_TIME.desc(), GOODS_CATEGORY.ID.desc())
                .page(page);
    }

    @Override
    public List<GoodsCategory> selectList(CommonStatusEnum status, GoodsCatLevelEnum level) {
        String statusName = status == null ? null : status.name();
        String levelName = level == null ? null : level.name();
        return queryChain().select(GOODS_CATEGORY.ID, GOODS_CATEGORY.NAME)
                .where(GOODS_CATEGORY.LEVEL.eq(levelName, If::hasText))
                .and(GOODS_CATEGORY.STATUS.eq(statusName, If::hasText))
                .list();
    }

    @Override
    public boolean save(GoodsCategory entity) {
        // 校验商品类别是否存在
        checkNameExist(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(GoodsCategory entity) {
        boolean update = StringUtils.isNotBlank(entity.getName());
        if (update) {
            // 校验商品类别是否存在
            checkNameExist(entity);
        }
        return super.updateById(entity);
    }

    /**
     * 校验商品类别是否存在
     */
    private void checkNameExist(GoodsCategory entity) {
        String queryName = queryChain().select(number(1))
                .from(GOODS_CATEGORY)
                .where(GOODS_CATEGORY.NAME.eq(entity.getName()))
                .and(GOODS_CATEGORY.ID.ne(entity.getId(), If::hasText))
                .limit(1).oneAs(String.class);
        if (existByCondition(queryName)) {
            throw new BaseRequestErrorException(BaseResultCodeEnum.ERROR_GOODS_CATEGORY_EXIST);
        }
    }
}




