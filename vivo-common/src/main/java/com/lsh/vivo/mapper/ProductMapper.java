package com.lsh.vivo.mapper;

import com.lsh.vivo.entity.Product;
import com.lsh.vivo.mapper.system.CommonMapper;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ASUS
 * @description 针对表【product(商品)】的数据库操作Mapper
 * @createLocalDateTime 2023-10-29 21:02:54
 * @Entity com.lsh.vivo.pojo.entity.Product
 */
@Mapper
public interface ProductMapper extends CommonMapper<Product> {

}




