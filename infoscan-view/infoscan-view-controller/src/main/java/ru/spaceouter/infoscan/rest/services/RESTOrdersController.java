package ru.spaceouter.infoscan.rest.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spaceouter.infoscan.dto.auth.UserAuthDTO;
import ru.spaceouter.infoscan.dto.orders.FullOrderDTO;
import ru.spaceouter.infoscan.dto.orders.UpdateOrderDTO;
import ru.spaceouter.infoscan.dto.view.PageableRequest;
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
    public ResponseEntity<?> getOrders(@RequestBody PageableRequest request
            , @CookieValue(name = "token", required = false) String token)
            throws UnauthorizedException {

        return found(ordersService.getOrders(
                getAuthDataByToken(token).getUserId(), request));
    }

    @GetMapping
    @RequestMapping(path = "{id}")
    public ResponseEntity<?> getOrder(@PathVariable("id") String id,
                                      @CookieValue(name = "token", required = false) String token)
            throws UnauthorizedException, WrongArgumentsException {

        if(!id.matches("^[\\d+]$"))
            throw new WrongArgumentsException();

        return found(ordersService.getOrder(
                Integer.parseInt(id),
                getAuthDataByToken(token).getUserId()));
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody FullOrderDTO fullOrderDTO,
                                         @CookieValue(name = "token", required = false) String token)
            throws UnauthorizedException{

        ordersService.createOrder(
                getAuthDataByToken(token).getUserId(),
                fullOrderDTO);
        return created();
    }

    @PutMapping
    public ResponseEntity<?> updateOrder(@RequestBody UpdateOrderDTO updateOrderDTO,
                                         @CookieValue(name = "token", required = false) String token)
            throws UnauthorizedException{

        ordersService.updateOrder(
                getAuthDataByToken(token).getUserId(),
                updateOrderDTO);

        return accepted();
    }

    @PutMapping("/sn")
    public ResponseEntity<?> updateOrderSocialNetwork(@RequestBody UpdateSocialNetworkDTO updateSocialNetworkDTO,
                                                      @CookieValue(name = "token", required = false) String token)
            throws UnauthorizedException{

        ordersService.updateSocialNetwork(updateSocialNetworkDTO.getOrderId(),
                updateSocialNetworkDTO.getSocialNetwork(),
                getAuthDataByToken(token).getUserId());

        return accepted();
    }

}
