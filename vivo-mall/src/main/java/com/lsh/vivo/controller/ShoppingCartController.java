package com.lsh.vivo.controller;

import com.lsh.vivo.bean.response.cart.CartVO;
import com.lsh.vivo.entity.ShoppingCart;
import com.lsh.vivo.mapper.struct.CartMpp;
import com.lsh.vivo.service.ShoppingCartService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
