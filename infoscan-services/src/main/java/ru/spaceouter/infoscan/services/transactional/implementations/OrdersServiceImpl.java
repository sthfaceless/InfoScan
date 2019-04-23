package ru.spaceouter.infoscan.services.transactional.implementations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.spaceouter.infoscan.dto.orders.FullOrderDTO;
import ru.spaceouter.infoscan.dto.orders.OrderField;
import ru.spaceouter.infoscan.dto.orders.SocialNetworkDTO;
import ru.spaceouter.infoscan.dto.orders.UpdateOrderDTO;
import ru.spaceouter.infoscan.dto.view.PageableRequest;
import ru.spaceouter.infoscan.dto.view.ViewOrderDTO;
import ru.spaceouter.infoscan.model.OrdersCustomDAO;
import ru.spaceouter.infoscan.model.hibernate.ProxyDAO;
import ru.spaceouter.infoscan.services.listeners.OrdersListener;
import ru.spaceouter.infoscan.services.transactional.OrdersService;

import java.util.List;

/**
 * @author danil
 * @date 20.04.19
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class OrdersServiceImpl implements OrdersService {

    private final OrdersCustomDAO ordersCustomDAO;
    private final ProxyDAO proxyDAO;

    private final OrdersListener ordersListener;

    private final int maxOrdersSize;

    public OrdersServiceImpl(OrdersCustomDAO ordersCustomDAO,
                             ProxyDAO proxyDAO,
                             OrdersListener ordersListener,
                             @Value("${orders.view.size}") Integer maxOrdersSize) {
        this.ordersCustomDAO = ordersCustomDAO;
        this.proxyDAO = proxyDAO;
        this.ordersListener = ordersListener;
        this.maxOrdersSize = maxOrdersSize;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ViewOrderDTO> getOrders(long userId, PageableRequest request) {

        return ordersCustomDAO.getOrdersByUser(userId, request.getStart(), maxOrdersSize);
    }

    @Transactional(readOnly = true)
    @Override
    public FullOrderDTO getOrder(long orderId, long userId) {

        return ordersCustomDAO.getFullOrder(orderId, userId);
    }

    @Override
    public void createOrder(long userId, FullOrderDTO fullOrderDTO) {

        long orderId = ordersCustomDAO.saveOrder(userId, fullOrderDTO);
        ordersListener.ordersCreated(orderId);
    }

    @Override
    public void updateOrder(long userId, UpdateOrderDTO update) {

        if(update.getField() == OrderField.SOCIAL_NETWORKS) return;

        ordersCustomDAO.updateOrder(update.getOrderId(),
                update.getField().toString(), update.getValue(),
                userId);

        ordersListener.orderUpdated(update.getOrderId(), update.getField());
    }

    @Override
    public void updateSocialNetwork(long orderId, SocialNetworkDTO socialNetworkDTO, long userId) {

        ordersCustomDAO.updateOrderSocialNetworks(orderId, socialNetworkDTO, userId);
        ordersListener.orderUpdated(orderId, OrderField.SOCIAL_NETWORKS);
    }
}
