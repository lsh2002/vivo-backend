package com.lsh.vivo.service.impl;

import com.lsh.vivo.service.ProductService;
import com.lsh.vivo.entity.Product;
import com.lsh.vivo.mapper.ProductMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【product(商品)】的数据库操作Service实现
* @createLocalDateTime 2023-10-28 22:51:50
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService {

}




