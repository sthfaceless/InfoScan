package ru.spaceouter.infoscan.services.transactional;

import ru.spaceouter.infoscan.dto.orders.FullOrderDTO;
import ru.spaceouter.infoscan.dto.orders.SocialNetworkDTO;
import ru.spaceouter.infoscan.dto.orders.UpdateOrderDTO;
import ru.spaceouter.infoscan.dto.view.PageableRequest;
import ru.spaceouter.infoscan.dto.view.ViewOrderDTO;

import java.util.List;

/**
 * @author danil
 * @date 20.04.19
 */
public interface OrdersService {

    List<ViewOrderDTO> getOrders(long userId, PageableRequest request);

    FullOrderDTO getOrder(long orderId, long userId);

    void createOrder(long userId, FullOrderDTO fullOrderDTO);

    void updateOrder(long userId, UpdateOrderDTO update);

    void updateSocialNetwork(long orderId, SocialNetworkDTO socialNetworkDTO, long userId);

}
