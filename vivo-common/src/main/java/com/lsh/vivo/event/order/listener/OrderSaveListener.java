package com.lsh.vivo.event.order.listener;

import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.event.order.bean.OrderSaveEvent;
import com.lsh.vivo.provider.ApplicationContextProvider;
import com.lsh.vivo.service.ShoppingCartService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

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
        List<String> skuIds = event.getSkuIds();
        String userId = event.getUserId();
        if (CollectionUtils.isNotEmpty(skuIds)) {
            ShoppingCartService shoppingCartService = ApplicationContextProvider.getBean(ShoppingCartService.class);
            shoppingCartService.updateChain()
                    .from(SHOPPING_CART)
                    .set(SHOPPING_CART.STATUS, CommonStatusEnum.T.name())
                    .where(SHOPPING_CART.USER_ID.eq(userId))
                    .and(SHOPPING_CART.SKU_ID.in(skuIds))
                    .update();
        }
    }
}
