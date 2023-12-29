package com.lsh.vivo.service.impl;

import com.lsh.vivo.entity.Order;
import com.lsh.vivo.mapper.OrderMapper;
import com.lsh.vivo.service.OrderService;
import com.lsh.vivo.service.system.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author ASUS
 * @description 针对表【order(订单)】的数据库操作Service实现
 * @createLocalDateTime 2023-10-28 22:51:47
 */
@Service
public class OrderServiceImpl extends CommonServiceImpl<OrderMapper, Order>
        implements OrderService {

}




