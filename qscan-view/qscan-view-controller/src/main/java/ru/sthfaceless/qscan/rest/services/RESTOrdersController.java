package ru.sthfaceless.qscan.rest.services;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.spaceouter.infoscan.dto.auth.UserAuthDTO;
import ru.spaceouter.infoscan.dto.orders.CreateOrderDTO;
import ru.spaceouter.infoscan.dto.orders.SocialNetworkDTO;
import ru.spaceouter.infoscan.dto.orders.UpdateOrderDTO;
import ru.spaceouter.infoscan.exceptions.InvalidAuthenticationException;
import ru.spaceouter.infoscan.exceptions.NotExistException;
import ru.spaceouter.infoscan.exceptions.UnauthorizedException;
import ru.spaceouter.infoscan.exceptions.WrongArgumentsException;
import ru.spaceouter.infoscan.rest.RestControllerWithAuthorization;
import ru.spaceouter.infoscan.services.transactional.AuthService;
import ru.spaceouter.infoscan.services.transactional.OrdersService;

/**
 * @author danil
 * @date 20.04.19
 */
@RestController
@RequestMapping("/api/orders")
public class RESTOrdersController extends RestControllerWithAuthorization<UserAuthDTO> {

    private final OrdersService ordersService;

    public RESTOrdersController(AuthService<UserAuthDTO> authService,
                                OrdersService ordersService) {
        super(authService);
        this.ordersService = ordersService;
    }

    @GetMapping
    public ResponseEntity<?> getOrders(@RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "query", required = false) String query,
            @RequestParam(name = "start", defaultValue = "0", required = false) String start,
            @RequestParam(name = "order", defaultValue = "date", required = false) String order,
            @RequestParam(name = "type", defaultValue = "1", required = false) String type,
            @CookieValue(name = "auth_token", required = false) String token)
            throws UnauthorizedException, InvalidAuthenticationException, WrongArgumentsException {

        if(!StringUtils.isEmpty(id))
            return found(ordersService.getOrder(
                    id, getAuthDataByToken(token).getUserId()));

        return found(ordersService.getOrders(
                getAuthDataByToken(token).getUserId(), query, start, order, type));
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderDTO createOrderDTO,
                                         @CookieValue(name = "auth_token", required = false) String token)
            throws UnauthorizedException, InvalidAuthenticationException {

        ordersService.createOrder(
                getAuthDataByToken(token).getUserId(),
                createOrderDTO);
        return created();
    }

    @PutMapping
    public ResponseEntity<?> updateOrder(@RequestBody UpdateOrderDTO updateOrderDTO,
                                         @CookieValue(name = "auth_token", required = false) String token)
            throws UnauthorizedException, InvalidAuthenticationException {

        ordersService.updateOrder(
                getAuthDataByToken(token).getUserId(),
                updateOrderDTO);

        return accepted();
    }

    @PutMapping("/sn")
    public ResponseEntity<?> updateOrderSocialNetwork(@RequestBody SocialNetworkDTO socialNetworkDTO,
                                                      @CookieValue(name = "auth_token", required = false) String token)
            throws UnauthorizedException, InvalidAuthenticationException, NotExistException {

        ordersService.updateSocialNetwork(socialNetworkDTO,
                getAuthDataByToken(token).getUserId());

        return accepted();
    }

}
