package com.lsh.vivo.service;

import com.alibaba.fastjson.JSONObject;
import com.lsh.vivo.bean.dto.order.OrderConditionDTO;
import com.lsh.vivo.entity.Order;
import com.lsh.vivo.enumerate.OrderStatusEnum;
import com.lsh.vivo.service.system.CommonService;
import com.mybatisflex.core.paginate.Page;

import java.util.Collection;
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

    boolean saveBatch(Collection<Order> entities, boolean cart, String requestNo);

    Page<Order> page(Page<Order> page, OrderConditionDTO orderConditionDTO);

    JSONObject getTodayData();

    JSONObject getMonthData();

    JSONObject getYearData();

    List<Order> listRefundOrder(String userId);

    Page<Order> pageAfterSales(Page<Order> page, OrderConditionDTO orderConditionDTO);
}
