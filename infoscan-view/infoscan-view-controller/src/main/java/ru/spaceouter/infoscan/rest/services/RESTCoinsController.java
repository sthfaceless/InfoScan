package ru.spaceouter.infoscan.rest.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spaceouter.infoscan.dto.auth.UserAuthDTO;
import ru.spaceouter.infoscan.exceptions.UnauthorizedException;
import ru.spaceouter.infoscan.rest.RestControllerWithAuthorization;
import ru.spaceouter.infoscan.services.transactional.AuthService;
import ru.spaceouter.infoscan.services.transactional.CoinsService;

/**
 * @author danil
 * @date 20.04.19
 */
@RestController
@RequestMapping("/api/coins")
public class RESTCoinsController extends RestControllerWithAuthorization<UserAuthDTO> {

    private final CoinsService coinsService;

    public RESTCoinsController(AuthService<UserAuthDTO> authService,
                               CoinsService coinsService) {
        super(authService);
        this.coinsService = coinsService;
    }

    @GetMapping
    public ResponseEntity<?> getCoins(@CookieValue(name = "token", required = false) String token)
            throws UnauthorizedException {

        return found(coinsService.getCoinsSize(
                getAuthDataByToken(token).getUserId()));
    }

    @GetMapping
    @RequestMapping(path = "/history")
    public ResponseEntity<?> getPaymentHistory(@CookieValue(name = "token", required = false) String token)
            throws UnauthorizedException {

        return found(coinsService.getPaymentsHistory(
                getAuthDataByToken(token).getUserId()));
    }

    @PostMapping
    public ResponseEntity<?> createPaymentRequest(@CookieValue(name = "token", required = false) String token)
            throws UnauthorizedException {

        coinsService.requestPayment(
                getAuthDataByToken(token).getUserId());

        return accepted();
    }

}
