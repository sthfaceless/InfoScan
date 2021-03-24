package ru.sthfaceless.qscan.services.listeners;

import ru.spaceouter.infoscan.dto.orders.OrderField;

/**
 * @author danil
 * @date 23.04.19
 */
public interface OrdersListener {

    void ordersCreated(long orderId);

    void orderUpdated(long orderId, OrderField field);

}
