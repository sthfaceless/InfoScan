package ru.spaceouter.infoscan.services.transactional;

import ru.spaceouter.infoscan.dto.orders.*;
import ru.spaceouter.infoscan.exceptions.NotExistException;
import ru.spaceouter.infoscan.exceptions.WrongArgumentsException;

import java.util.List;

/**
 * @author danil
 * @date 20.04.19
 */
public interface OrdersService {

    List<ViewOrderDTO> getOrders(long userId, String query, String start, String order, String type) throws WrongArgumentsException;

    FullOrderDTO getOrder(String orderId, long userId) throws WrongArgumentsException;

    void createOrder(long userId, CreateOrderDTO createOrderDTO);

    void updateOrder(long userId, UpdateOrderDTO update);

    void updateSocialNetwork(SocialNetworkDTO socialNetworkDTO, long userId) throws NotExistException;

}
