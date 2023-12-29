package com.lsh.vivo.mapper;

import com.lsh.vivo.entity.SeckillProduct;
import com.lsh.vivo.mapper.system.CommonMapper;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ASUS
 * @description 针对表【seckill_product(商品秒杀)】的数据库操作Mapper
 * @createLocalDateTime 2023-10-29 21:03:04
 * @Entity com.lsh.vivo.pojo.entity.SeckillProduct
 */
@Mapper
public interface SeckillProductMapper extends CommonMapper<SeckillProduct> {

}




