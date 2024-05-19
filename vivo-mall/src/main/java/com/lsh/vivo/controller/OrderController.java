package com.lsh.vivo.controller;

import com.lsh.vivo.bean.request.order.OrderSaveVO;
import com.lsh.vivo.bean.request.order.OrderStatusVO;
import com.lsh.vivo.bean.request.order.OrderTypeVO;
import com.lsh.vivo.bean.response.order.OrderVO;
import com.lsh.vivo.entity.Order;
import com.lsh.vivo.enumerate.OrderStatusEnum;
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
    public OrderVO saveOrder(@NotNull @RequestBody OrderSaveVO orderSaveVO) {
        Order newOrder = OrderMpp.INSTANCE.toDO(orderSaveVO);
        orderService.save(newOrder);
        return OrderMpp.INSTANCE.toVO(newOrder);
    }

    @PutMapping("/status")
    public void changeStatus(@NotNull @RequestBody OrderStatusVO orderStatusVO) {
        orderService.updateStatus(orderStatusVO.getOrderId(), orderStatusVO.getStatus(), orderStatusVO.getTime());
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
