package com.lsh.vivo.mapper;

import com.lsh.vivo.entity.GoodsSeckill;
import com.lsh.vivo.mapper.system.CommonMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ASUS
 * @description 针对表【goods_seckill(商品秒杀)】的数据库操作Mapper
 * @createLocalDateTime 2023-10-29 21:03:04
 * @Entity com.lsh.vivo.pojo.entity.SeckillGoods
 */
@Mapper
public interface GoodsSeckillMapper extends CommonMapper<GoodsSeckill> {

}




