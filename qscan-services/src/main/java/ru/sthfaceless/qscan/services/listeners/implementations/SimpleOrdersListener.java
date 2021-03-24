package ru.sthfaceless.qscan.services.listeners.implementations;

import org.springframework.stereotype.Component;
import ru.spaceouter.infoscan.dto.orders.OrderField;
import ru.spaceouter.infoscan.services.listeners.OrdersListener;

/**
 * @author danil
 * @date 23.04.19
 */
@Component
public class SimpleOrdersListener implements OrdersListener {

    @Override
    public void ordersCreated(long orderId) {

    }

    @Override
    public void orderUpdated(long orderId, OrderField field) {

    }
}
