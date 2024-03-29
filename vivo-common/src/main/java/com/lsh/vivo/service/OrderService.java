package com.lsh.vivo.service;

import com.lsh.vivo.entity.Order;
import com.lsh.vivo.enumerate.OrderStatusEnum;
import com.lsh.vivo.service.system.CommonService;

import java.util.List;

/**
 * @author ASUS
 * @description 针对表【order(订单)】的数据库操作Service
 * @createLocalDateTime 2023-10-28 22:51:47
 */
public interface OrderService extends CommonService<Order> {


    List<Order> listLastOrder(String userId);

    List<Order> listOrder(String userId, String status);

    void updateStatus(List<String> orderIds, OrderStatusEnum orderStatusEnum, Long time);

    int countUnpaid(String userId);
}
