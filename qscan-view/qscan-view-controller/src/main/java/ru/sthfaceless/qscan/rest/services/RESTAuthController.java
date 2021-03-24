package ru.sthfaceless.qscan.rest.services;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spaceouter.infoscan.dto.auth.AuthCredentialsDTO;
import ru.spaceouter.infoscan.dto.auth.AuthTokenDTO;
import ru.spaceouter.infoscan.dto.auth.UserAuthDTO;
import ru.spaceouter.infoscan.exceptions.InvalidAuthenticationException;
import ru.spaceouter.infoscan.exceptions.UnauthorizedException;
import ru.spaceouter.infoscan.rest.AbstractRestController;
import ru.spaceouter.infoscan.services.transactional.AuthService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
    public ResponseEntity<?> authWithCredentials(@Valid @RequestBody AuthCredentialsDTO authCredentialsDTO,
                                                 BindingResult bindingResult,
                                                 HttpServletResponse response) throws InvalidAuthenticationException {

        if(bindingResult.hasErrors())
            throw new InvalidAuthenticationException();

        return found(authService.authWithCredentials(authCredentialsDTO, response));
    }

    @PostMapping(path = "/token")
    public ResponseEntity<?> authWithToken(@RequestBody AuthTokenDTO authTokenDTO,
                                           HttpServletResponse response) throws InvalidAuthenticationException {

        return found(authService.authWithToken(authTokenDTO, response));
    }

    @PostMapping(path = "/logout")
    public ResponseEntity<?> logout(@CookieValue(name = "auth_token", required = false) String token,
                                    HttpServletResponse response)
            throws UnauthorizedException {

        authService.logout(token, response);
        return ok();
    }

}
