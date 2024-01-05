package com.lsh.vivo.service;

import com.lsh.vivo.entity.GoodsCategory;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.enumerate.GoodsCatLevelEnum;
import com.lsh.vivo.service.system.CommonService;
import com.mybatisflex.core.paginate.Page;

import java.util.List;

/**
 * @author ASUS
 * @description 针对表【goods_category(商品类别)】的数据库操作Service
 * @createLocalDateTime 2023-10-28 22:51:52
 */
public interface GoodsCategoryService extends CommonService<GoodsCategory> {

    /**
     * 分页查询
     *
     * @param page   分页参数
     * @param name   商品类别名称
     * @param level  类目级别
     * @param status 状态
     * @return 分页数据
     */
    Page<GoodsCategory> page(Page<GoodsCategory> page, String name, GoodsCatLevelEnum level, CommonStatusEnum status);

    /**
     * 查询商品类别下拉菜单
     *
     * @param status 状态
     * @return 商品类别下拉菜单
     */
    List<GoodsCategory> selectList(CommonStatusEnum status, GoodsCatLevelEnum level);
}
