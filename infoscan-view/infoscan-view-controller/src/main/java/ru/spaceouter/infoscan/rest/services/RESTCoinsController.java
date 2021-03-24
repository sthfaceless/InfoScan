package ru.spaceouter.infoscan.rest.services;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spaceouter.infoscan.dto.auth.UserAuthDTO;
import ru.spaceouter.infoscan.dto.coins.BillingInfo;
import ru.spaceouter.infoscan.dto.coins.PaymentRequest;
import ru.spaceouter.infoscan.exceptions.InvalidAuthenticationException;
import ru.spaceouter.infoscan.exceptions.UnauthorizedException;
import ru.spaceouter.infoscan.exceptions.WrongArgumentsException;
import ru.spaceouter.infoscan.rest.RestControllerWithAuthorization;
import ru.spaceouter.infoscan.services.transactional.AuthService;
import ru.spaceouter.infoscan.services.transactional.CoinsService;

import javax.validation.Valid;

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
    public ResponseEntity<?> getCoins(@CookieValue(name = "auth_token", required = false) String token)
            throws UnauthorizedException, InvalidAuthenticationException {

        return found(coinsService.getCoinsSize(
                getAuthDataByToken(token).getUserId()));
    }

    @PostMapping(path = "/history")
    public ResponseEntity<?> getPaymentHistory(@CookieValue(name = "auth_token", required = false) String token,
                                               @RequestParam(name="start", defaultValue = "0",required = false) String start,
                                               @RequestParam(name = "order", defaultValue = "date",required = false) String order,
                                               @RequestParam(name = "type", defaultValue = "1", required = false) String type)
            throws UnauthorizedException, InvalidAuthenticationException{

        return found(coinsService.getPaymentsHistory(
                getAuthDataByToken(token).getUserId(), start, order, type));
    }

    @PostMapping
    public ResponseEntity<?> createPaymentRequest(@CookieValue(name = "auth_token", required = false) String token,
                                                  @RequestBody @Valid PaymentRequest paymentRequest, BindingResult bindingResult)
            throws UnauthorizedException, InvalidAuthenticationException, WrongArgumentsException {

        if(bindingResult.hasErrors())
            throw new WrongArgumentsException();

        coinsService.requestPayment(
                getAuthDataByToken(token).getUserId(), paymentRequest);

        return accepted();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBillingInfo(@CookieValue(name = "auth_token", required = false) String token,
                                            @RequestBody @Valid BillingInfo billingInfo, BindingResult bindingResult)
            throws UnauthorizedException, InvalidAuthenticationException, WrongArgumentsException {

        if(bindingResult.hasErrors())
            throw new WrongArgumentsException();

        coinsService.addBillingInfo(
                getAuthDataByToken(token).getUserId(), billingInfo);

        return accepted();
    }

}
