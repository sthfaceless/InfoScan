package ru.spaceouter.infoscan.model;

import ru.spaceouter.infoscan.dto.orders.FullOrderDTO;
import ru.spaceouter.infoscan.dto.orders.SocialNetworkDTO;
import ru.spaceouter.infoscan.dto.view.ViewOrderDTO;

import java.util.List;

/**
 * @author danil
 * @date 23.04.19
 */
public interface OrdersCustomDAO  {


    List<ViewOrderDTO> getOrdersByUser(long userId, int start, int max);

    FullOrderDTO getFullOrder(long orderId, long userId);

    List<SocialNetworkDTO> getSocialNetwork(long orderId, long userId);

    long saveOrder(long userId, FullOrderDTO fullOrderDTO);

    boolean updateOrder(long orderId, String fieldName, String value, long userId);

    long updateOrderSocialNetworks(SocialNetworkDTO socialNetworkDTO, long userId);
}
