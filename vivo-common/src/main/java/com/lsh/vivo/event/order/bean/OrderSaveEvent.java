package com.lsh.vivo.event.order.bean;

import com.lsh.vivo.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * @author AdolphLv
 * @version 1.0.0
 * 2023/10/1 21:20
 */
@Setter
@Getter
public class OrderSaveEvent extends ApplicationEvent {

    private String orderId;

    private List<OrderItem> orderItems;

    private boolean cart;

    public OrderSaveEvent(Object source) {
        super(source);
    }

}
