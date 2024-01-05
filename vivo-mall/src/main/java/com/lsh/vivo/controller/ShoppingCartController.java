//package com.lsh.vivo.controller;
//
//import com.lsh.vivo.vo.CartVO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * @author lsh
// * @since 2023-08-19 13:27
// */
//@RestController
//@RequestMapping("/cart")
//@RequiredArgsConstructor
//public class ShoppingCartController {
//
//    private final BaseResponse resultMessage;
//    private final ShoppingCartService cartService;
//
//    /**
//     * 获取购物车信息
//     *
//     * @param userId
//     * @return
//     */
//    @GetMapping("/user/{userId}")
//    public BaseResponse cart(@PathVariable String userId) {
//        List<CartVO> carts = cartService.listCartByUserId(userId);
//        resultMessage.success("001", carts);
//        return resultMessage;
//    }
//
//    /**
//     * 添加购物车
//     *
//     * @param goodsId
//     * @param userId
//     * @return
//     */
//    @PostMapping("/goods/user/{goodsId}/{userId}")
//    public BaseResponse cart(@PathVariable String goodsId, @PathVariable String userId) {
//        CartVO cartVo = cartService.saveCart(goodsId, userId);
//        if (cartVo != null) {
//            resultMessage.success("001", "添加购物车成功", cartVo);
//        } else {
//            resultMessage.success("002", "该商品已经在购物车，数量+1");
//        }
//        return resultMessage;
//    }
//
//    @PutMapping("/user/num/{cartId}/{userId}/{num}")
//    public BaseResponse cart(@PathVariable String cartId, @PathVariable String userId, @PathVariable String num) {
//        cartService.updateCartNum(cartId, userId, num);
//        resultMessage.success("001", "更新成功");
//        return resultMessage;
//    }
//
//    @DeleteMapping("/user/{cartId}/{userId}")
//    public BaseResponse deleteCart(@PathVariable String cartId, @PathVariable String userId) {
//        cartService.removeCart(cartId, userId);
//        resultMessage.success("001", "删除成功");
//        return resultMessage;
//    }
//}
