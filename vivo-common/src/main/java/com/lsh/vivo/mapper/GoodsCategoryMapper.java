package com.lsh.vivo.mapper;

import com.lsh.vivo.entity.GoodsCategory;
import com.lsh.vivo.mapper.system.CommonMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ASUS
 * @description 针对表【goods_category(商品类别)】的数据库操作Mapper
 * @createLocalDateTime 2023-10-29 21:02:57
 * @Entity com.lsh.vivo.pojo.entity.GoodsCategory
 */
@Mapper
public interface GoodsCategoryMapper extends CommonMapper<GoodsCategory> {

}




