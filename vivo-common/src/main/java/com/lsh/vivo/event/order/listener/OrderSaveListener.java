package com.lsh.vivo.event.order.listener;

import com.lsh.vivo.entity.OrderItem;
import com.lsh.vivo.event.order.bean.OrderSaveEvent;
import com.lsh.vivo.provider.ApplicationContextProvider;
import com.lsh.vivo.service.OrderItemService;
import com.lsh.vivo.service.ShoppingCartService;
import com.lsh.vivo.util.OauthContext;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.lsh.vivo.bean.constant.GlobalConstant.HTTP_USER_ID;
import static com.lsh.vivo.entity.table.ShoppingCartTableDef.SHOPPING_CART;


/**
 * @author AdolphLv
 * @version 1.0.0
 * 2023/10/1 21:37
 */
@Component
public class OrderSaveListener implements ApplicationListener<OrderSaveEvent> {

    @Async("threadPool")
    @Override
    public void onApplicationEvent(OrderSaveEvent event) {
        List<OrderItem> orderItems = event.getOrderItems();
        String orderId = event.getOrderId();
        if (CollectionUtils.isNotEmpty(orderItems)) {
            if (event.isCart()) {
                List<String> skuIds = orderItems.stream().map(OrderItem::getSkuId).toList();
                ShoppingCartService shoppingCartService = ApplicationContextProvider.getBean(ShoppingCartService.class);
                shoppingCartService.remove(SHOPPING_CART.SKU_ID.in(skuIds)
                        .and(SHOPPING_CART.USER_ID.eq(OauthContext.get(HTTP_USER_ID)))
                );
            }
            List<OrderItem> newOrderItems = new ArrayList<>(orderItems.size());
            for (OrderItem orderItem : orderItems) {
                OrderItem newOrderItem = new OrderItem();
                newOrderItem.setUserId((String) OauthContext.get(HTTP_USER_ID));
                newOrderItem.setOrderId(orderId);
                newOrderItem.setSkuId(orderItem.getSkuId());
                newOrderItem.setCount(orderItem.getCount());
                newOrderItem.setTotalPrice(orderItem.getTotalPrice());
                newOrderItems.add(newOrderItem);
            }
            OrderItemService orderItemService = ApplicationContextProvider.getBean(OrderItemService.class);
            orderItemService.saveBatch(newOrderItems);
        }
    }
}
