package com.lsh.vivo.mapper.struct;

import com.lsh.vivo.bean.request.cart.CartSaveVO;
import com.lsh.vivo.bean.request.cart.CartUpdateVO;
import com.lsh.vivo.bean.response.cart.CartVO;
import com.lsh.vivo.bean.response.goods.GoodsSkuVO;
import com.lsh.vivo.bean.response.goods.GoodsVO;
import com.lsh.vivo.entity.Goods;
import com.lsh.vivo.entity.GoodsSku;
import com.lsh.vivo.entity.ShoppingCart;
import com.lsh.vivo.util.MapperStructTypeConvert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 角色mapper
 *
 * @author pjw
 * @version 1.0.0
 * @since 2023-10-03 16:53
 */
@Mapper(uses = {MapperStructTypeConvert.class})
public interface CartMpp {

    /**
     * 定义实例
     */
    CartMpp INSTANCE = Mappers.getMapper(CartMpp.class);

    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "localDateTimeToLong")
    @Mapping(target = "modifierTime", source = "modifierTime", qualifiedByName = "localDateTimeToLong")
    CartVO toVO(ShoppingCart ShoppingCart);

    List<CartVO> toVO(List<ShoppingCart> ShoppingCarts);

    ShoppingCart toDO(CartSaveVO cartSaveVO);

    ShoppingCart toDO(CartUpdateVO cartUpdateVO);

    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "localDateTimeToLong")
    @Mapping(target = "modifierTime", source = "modifierTime", qualifiedByName = "localDateTimeToLong")
    GoodsSkuVO toVO(GoodsSku goodsSku);

    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "localDateTimeToLong")
    @Mapping(target = "modifierTime", source = "modifierTime", qualifiedByName = "localDateTimeToLong")
    GoodsVO toVO(Goods goods);
}
