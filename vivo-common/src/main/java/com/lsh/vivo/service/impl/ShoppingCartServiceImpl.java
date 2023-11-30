package com.lsh.vivo.service.impl;

import com.lsh.vivo.service.ShoppingCartService;
import com.lsh.vivo.entity.ShoppingCart;
import com.lsh.vivo.mapper.ShoppingCartMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【shopping_cart(购物车)】的数据库操作Service实现
* @createLocalDateTime 2023-10-28 22:52:00
*/
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart>
    implements ShoppingCartService {

}




