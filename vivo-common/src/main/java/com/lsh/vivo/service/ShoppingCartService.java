package com.lsh.vivo.service;

import com.lsh.vivo.entity.ShoppingCart;
import com.lsh.vivo.service.system.CommonService;

import java.util.List;

/**
 * @author ASUS
 * @description 针对表【shopping_cart(购物车)】的数据库操作Service
 * @createLocalDateTime 2023-10-28 22:52:00
 */
public interface ShoppingCartService extends CommonService<ShoppingCart> {

    List<ShoppingCart> listByUserId(String userId);
}
