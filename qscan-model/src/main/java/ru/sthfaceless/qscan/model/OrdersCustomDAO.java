package ru.sthfaceless.qscan.model;

import ru.spaceouter.infoscan.dto.orders.FullOrderDTO;
import ru.spaceouter.infoscan.dto.orders.SocialNetworkDTO;
import ru.spaceouter.infoscan.dto.orders.ViewOrderDTO;
import ru.spaceouter.infoscan.exceptions.NotExistException;

import java.util.List;

/**
 * @author danil
 * @date 23.04.19
 */
public interface OrdersCustomDAO  {


    List<ViewOrderDTO> getOrdersByUser(long userId, String key, int start, int size, String order, String type);

    FullOrderDTO getFullOrder(long orderId, long userId);

    List<SocialNetworkDTO> getSocialNetwork(long orderId, long userId);

    boolean updateOrder(long orderId, String fieldName, String value, long userId);

    long updateOrderSocialNetworks(SocialNetworkDTO socialNetworkDTO, long userId) throws NotExistException;
}
