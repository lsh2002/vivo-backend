package com.lsh.vivo.service;


import com.lsh.vivo.entity.GoodsSku;
import com.lsh.vivo.enumerate.GoodsStatusEnum;
import com.lsh.vivo.service.system.CommonService;
import com.mybatisflex.core.paginate.Page;

/**
 * @author ASUS
 * @description 针对表【goods_sku】的数据库操作Service
 * @createDate 2024-01-02 09:53:30
 */
public interface GoodsSkuService extends CommonService<GoodsSku> {

    /**
     * 分页查询
     *
     * @param page   分页对象
     * @param name   商品名称
     * @param status 商品状态
     * @return 分页对象
     */
    Page<GoodsSku> page(Page<GoodsSku> page, String name, GoodsStatusEnum status);
}
