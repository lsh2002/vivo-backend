package com.lsh.vivo.controller;

import com.lsh.vivo.bean.request.order.OrderSaveVO;
import com.lsh.vivo.bean.request.order.OrderStatusVO;
import com.lsh.vivo.bean.request.order.OrderTypeVO;
import com.lsh.vivo.bean.response.order.OrderVO;
import com.lsh.vivo.entity.Order;
import com.lsh.vivo.enumerate.BaseResultCodeEnum;
import com.lsh.vivo.enumerate.OrderStatusEnum;
import com.lsh.vivo.exception.BaseRequestErrorException;
import com.lsh.vivo.mapper.struct.OrderMpp;
import com.lsh.vivo.service.OrderService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lsh
 * @since 2023-08-19 13:25
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/last")
    public List<OrderVO> listLastOrder(@NotNull String userId) {
        List<Order> orders = orderService.listLastOrder(userId);
        return OrderMpp.INSTANCE.toVO(orders);
    }

    @GetMapping
    public List<OrderVO> listOrder(@NotNull OrderStatusEnum status, @NotNull String userId) {
        String status1 = OrderStatusEnum.A.equals(status) ? "" : status.name();
        List<Order> orders = orderService.listOrder(userId, status1);
        return OrderMpp.INSTANCE.toVO(orders);
    }

    @PostMapping
    public List<OrderVO> saveOrder(@NotEmpty @RequestBody List<OrderSaveVO> orderSaveVOs) {
        boolean cart = orderSaveVOs.get(0).isCart();
        List<Order> newOrders = OrderMpp.INSTANCE.toDO(orderSaveVOs);
        boolean saveSuccess = orderService.saveBatch(newOrders, cart);
        if (!saveSuccess) {
            throw new BaseRequestErrorException(BaseResultCodeEnum.ERROR);
        }
        return OrderMpp.INSTANCE.toVO(newOrders);
    }

    @PutMapping("/status")
    public void changeStatus(@NotNull @RequestBody OrderStatusVO orderStatusVO) {
        orderService.updateStatus(orderStatusVO.getOrderIds(), orderStatusVO.getStatus(), orderStatusVO.getTime());
    }

    @DeleteMapping
    public void remove(@NotEmpty @RequestBody List<String> orderIds) {
        orderService.removeByIds(orderIds);
    }

    @GetMapping("/unpaidCount")
    public Integer countUnpaid(@NotNull String userId) {
        return orderService.countUnpaid(userId);
    }

    @GetMapping("/refund")
    public List<OrderVO> listRefundOrder(@NotNull String userId) {
        List<Order> orders = orderService.listRefundOrder(userId);
        return OrderMpp.INSTANCE.toVO(orders);
    }

    @PutMapping("/type")
    public void changeType(@NotNull @RequestBody OrderTypeVO orderTypeVO) {
        Order newOrder = OrderMpp.INSTANCE.toDO(orderTypeVO);
        orderService.updateById(newOrder);
    }

}
