package com.lsh.vivo.service.impl;

import com.lsh.vivo.service.ProductCategoryService;
import com.lsh.vivo.entity.ProductCategory;
import com.lsh.vivo.mapper.ProductCategoryMapper;
import com.lsh.vivo.service.system.impl.CommonServiceImpl;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author ASUS
 * @description 针对表【product_category(商品类别)】的数据库操作Service实现
 * @createLocalDateTime 2023-10-28 22:51:52
 */
@Service
public class ProductCategoryServiceImpl extends CommonServiceImpl<ProductCategoryMapper, ProductCategory>
        implements ProductCategoryService {

}




