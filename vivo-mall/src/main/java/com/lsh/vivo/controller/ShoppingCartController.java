package com.lsh.vivo.controller;

import com.lsh.vivo.bean.request.cart.CartSaveVO;
import com.lsh.vivo.bean.request.cart.CartUpdateVO;
import com.lsh.vivo.bean.response.cart.CartVO;
import com.lsh.vivo.entity.ShoppingCart;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.mapper.struct.CartMpp;
import com.lsh.vivo.service.ShoppingCartService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lsh.vivo.entity.table.ShoppingCartTableDef.SHOPPING_CART;

/**
 * @author lsh
 * @since 2023-08-19 13:27
 */
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @GetMapping
    public List<CartVO> list(@NotNull String userId) {
        List<ShoppingCart> carts = shoppingCartService.listByUserId(userId);
        return CartMpp.INSTANCE.toVO(carts);
    }

    @PostMapping
    public void save(@NotNull @RequestBody CartSaveVO cartSaveVO) {
        ShoppingCart newCart = CartMpp.INSTANCE.toDO(cartSaveVO);
        shoppingCartService.save(newCart);
    }

    @PutMapping
    public void update(@NotNull @RequestBody CartUpdateVO cartUpdateVO) {
        ShoppingCart cart = CartMpp.INSTANCE.toDO(cartUpdateVO);
        shoppingCartService.updateById(cart);
    }

    @PutMapping("/delete")
    public void delete(@NotNull String id) {
        shoppingCartService.updateChain()
                .from(SHOPPING_CART)
                .set(SHOPPING_CART.STATUS, CommonStatusEnum.T.name())
                .where(SHOPPING_CART.ID.eq(id))
                .update();
    }
}
