package com.lsh.vivo.service;


import com.lsh.vivo.entity.GoodsSku;
import com.lsh.vivo.enumerate.GoodsStatusEnum;
import com.lsh.vivo.enumerate.StockStatusEnum;
import com.lsh.vivo.service.system.CommonService;
import com.mybatisflex.core.paginate.Page;

import java.util.List;

/**
 * @author ASUS
 * @description 针对表【goods_sku】的数据库操作Service
 * @createDate 2024-01-02 09:53:30
 */
public interface GoodsSkuService extends CommonService<GoodsSku> {

    /**
     * 分页查询（后台）
     *
     * @param page   分页对象
     * @param name   商品名称
     * @param status 商品状态
     * @return 分页对象
     */
    Page<GoodsSku> page(Page<GoodsSku> page, String name, GoodsStatusEnum status, String memory, String color);


    void updateStock(String id, Integer stockChange, StockStatusEnum stockStatus);

    /**
     * 分页查询（前台）
     *
     * @param page   分页对象
     * @return 分页对象
     */
    Page<GoodsSku> page(Page<GoodsSku> page, String keyword, String categoryId);


    List<GoodsSku> listByGoodsId(String goodsId);

    String getByAttribute(String color, String memory, String goodsId);

    List<String> getAllAttribute(String goodsId);

    List<GoodsSku> listSelect(GoodsStatusEnum status);

    List<GoodsSku> listStatistics();

}
