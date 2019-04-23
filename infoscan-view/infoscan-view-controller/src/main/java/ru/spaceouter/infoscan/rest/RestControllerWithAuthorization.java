package ru.spaceouter.infoscan.rest;

import ru.spaceouter.infoscan.exceptions.UnauthorizedException;
import ru.spaceouter.infoscan.services.transactional.AuthService;

/**
 * @author danil
 * @date 21.04.19
 */
public class RestControllerWithAuthorization<T> extends AbstractRestController {

    private final AuthService<T> authService;

    public RestControllerWithAuthorization(AuthService<T> authService) {
        this.authService = authService;
    }

    protected T getAuthDataByToken(String token) throws UnauthorizedException{

        return authService.getAuthUser(token);
    }

}
