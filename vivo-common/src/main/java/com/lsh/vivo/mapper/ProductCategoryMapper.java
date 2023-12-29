package com.lsh.vivo.mapper;

import com.lsh.vivo.entity.ProductCategory;
import com.lsh.vivo.mapper.system.CommonMapper;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ASUS
 * @description 针对表【product_category(商品类别)】的数据库操作Mapper
 * @createLocalDateTime 2023-10-29 21:02:57
 * @Entity com.lsh.vivo.pojo.entity.ProductCategory
 */
@Mapper
public interface ProductCategoryMapper extends CommonMapper<ProductCategory> {

}




