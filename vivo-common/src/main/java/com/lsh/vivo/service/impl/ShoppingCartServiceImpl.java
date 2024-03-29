package com.lsh.vivo.service.impl;

import com.lsh.vivo.entity.ShoppingCart;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.mapper.ShoppingCartMapper;
import com.lsh.vivo.service.ShoppingCartService;
import com.lsh.vivo.service.system.impl.CommonServiceImpl;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lsh.vivo.entity.table.ShoppingCartTableDef.SHOPPING_CART;
import static com.mybatisflex.core.query.QueryMethods.select;

/**
 * @author ASUS
 * @description 针对表【shopping_cart(购物车)】的数据库操作Service实现
 * @createLocalDateTime 2023-10-28 22:52:00
 */
@Service
public class ShoppingCartServiceImpl extends CommonServiceImpl<ShoppingCartMapper, ShoppingCart>
        implements ShoppingCartService {

    @Override
    public List<ShoppingCart> listByUserId(String userId) {
        QueryWrapper queryWrapper = select()
                .from(SHOPPING_CART)
                .where(SHOPPING_CART.USER_ID.eq(userId))
                .and(SHOPPING_CART.STATUS.ne(CommonStatusEnum.T.name()))
                .orderBy(SHOPPING_CART.CREATE_TIME.desc());
        return mapper.selectListWithRelationsByQuery(queryWrapper);
    }
}




