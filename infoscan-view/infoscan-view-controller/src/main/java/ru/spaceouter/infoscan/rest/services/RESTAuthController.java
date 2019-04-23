package ru.spaceouter.infoscan.rest.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spaceouter.infoscan.dto.auth.UserAuthDTO;
import ru.spaceouter.infoscan.dto.view.AuthCredentialsDTO;
import ru.spaceouter.infoscan.dto.view.AuthTokenDTO;
import ru.spaceouter.infoscan.exceptions.InvalidAuthenticationException;
import ru.spaceouter.infoscan.exceptions.UnauthorizedException;
import ru.spaceouter.infoscan.rest.AbstractRestController;
import ru.spaceouter.infoscan.services.transactional.AuthService;

import javax.servlet.http.HttpServletResponse;

/**
 * @author danil
 * @date 22.04.19
 */
@RestController
@RequestMapping("/api/auth")
public class RESTAuthController extends AbstractRestController {

    private final AuthService<UserAuthDTO> authService;

    public RESTAuthController(AuthService<UserAuthDTO> authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> authWithCredentials(@RequestBody AuthCredentialsDTO authCredentialsDTO,
                                                 HttpServletResponse response) throws InvalidAuthenticationException {

        return found(authService.authWithCredentials(authCredentialsDTO, response));
    }

    @PostMapping(path = "/token")
    public ResponseEntity<?> authWithToken(@RequestBody AuthTokenDTO authTokenDTO,
                                           HttpServletResponse response) throws InvalidAuthenticationException {

        return found(authService.authWithToken(authTokenDTO, response));
    }

    @PostMapping(path = "/logout")
    public ResponseEntity<?> logout(@CookieValue(name = "token", required = false) String token,
                                    HttpServletResponse response)
            throws UnauthorizedException {

        authService.logout(token, response);
        return ok();
    }

}
