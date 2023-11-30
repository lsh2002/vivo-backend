//package com.lsh.vivo.service;
//
//import com.baomidou.mybatisplus.extension.service.IService;
//import com.lsh.vivo.pojo.SeckillProduct;
//import com.lsh.vivo.pojo.SeckillTime;
//import com.lsh.vivo.vo.SeckillProductVO;
//
//import java.util.List;
//
///**
// * @author lsh
// * @description 针对表【seckill_product】的数据库操作Service
// * @since 2023-08-11 14:40
// */
//public interface SeckillProductService extends IService<SeckillProduct> {
//
//    /**
//     * 获取秒杀商品列表
//     *
//     * @param timeId 秒杀时间
//     * @return 秒杀商品列表
//     */
//    List<SeckillProductVO> listSeckillProduct(String timeId);
//
//    /**
//     * 新增秒杀商品
//     *
//     * @param seckillProduct 秒杀商品
//     */
//    void saveSeckillProduct(SeckillProduct seckillProduct);
//
//    /**
//     * 当前时间及往后7个时间段, 总共8个
//     *
//     * @return 秒杀时间段
//     */
//    List<SeckillTime> listTime();
//
//    /**
//     * 获取秒杀商品
//     *
//     * @param seckillId 秒杀
//     * @return 秒杀商品
//     */
//    SeckillProductVO getSeckill(String seckillId) throws Exception;
//
//    /**
//     * 秒杀商品
//     * @param seckillId 秒杀
//     * @param userId 用户
//     */
//    void seckillProduct(String seckillId, Integer userId) throws Exception;
//
//    /**
//     * 获取秒杀结束时间
//     * @param seckillId
//     * @return
//     */
//    Long getEndTime(String seckillId);
//}
