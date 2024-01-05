package com.lsh.vivo.service;

import com.lsh.vivo.bean.dto.goods.GoodsConditionDTO;
import com.lsh.vivo.entity.Goods;
import com.lsh.vivo.service.system.CommonService;
import com.mybatisflex.core.paginate.Page;

/**
 * @author ASUS
 * @description 针对表【goods(商品)】的数据库操作Service
 * @createLocalDateTime 2023-10-28 22:51:50
 */
public interface GoodsService extends CommonService<Goods> {

    /**
     * 分页查询
     *
     * @param page              分页对象
     * @param goodsConditionDTO 查询条件
     * @return 分页对象
     */
    Page<Goods> page(Page<Goods> page, GoodsConditionDTO goodsConditionDTO);
}
