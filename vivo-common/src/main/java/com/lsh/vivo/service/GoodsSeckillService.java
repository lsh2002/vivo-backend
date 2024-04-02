package com.lsh.vivo.service;

import com.lsh.vivo.entity.GoodsSeckill;
import com.lsh.vivo.enumerate.GoodsStatusEnum;
import com.lsh.vivo.service.system.CommonService;
import com.mybatisflex.core.paginate.Page;

/**
 * @author ASUS
 * @description 针对表【goods_seckill(商品秒杀)】的数据库操作Service
 * @createLocalDateTime 2023-10-28 22:51:58
 */
public interface GoodsSeckillService extends CommonService<GoodsSeckill> {

    Page<GoodsSeckill> page(Page<GoodsSeckill> page, String name, GoodsStatusEnum status, Long startTime, Long endTime);
}
