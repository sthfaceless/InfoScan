package ru.spaceouter.infoscan.services.transactional;

import ru.spaceouter.infoscan.dto.auth.AuthCredentialsDTO;
import ru.spaceouter.infoscan.dto.auth.AuthTokenDTO;
import ru.spaceouter.infoscan.dto.auth.UserAuthDTO;
import ru.spaceouter.infoscan.exceptions.InvalidAuthenticationException;
import ru.spaceouter.infoscan.exceptions.UnauthorizedException;

import javax.servlet.http.HttpServletResponse;

/**
 * @author danil
 * @date 21.04.19
 */
public interface AuthService<T> {

    T getAuthUser(String token) throws UnauthorizedException, InvalidAuthenticationException;

    UserAuthDTO authWithCredentials(AuthCredentialsDTO authCredentialsDTO, HttpServletResponse response) throws InvalidAuthenticationException;

    UserAuthDTO authWithToken(AuthTokenDTO authTokenDTO, HttpServletResponse response) throws InvalidAuthenticationException;

    void logout(String token, HttpServletResponse response) throws UnauthorizedException;

}
