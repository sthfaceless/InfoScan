package ru.spaceouter.infoscan.rest.services;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spaceouter.infoscan.dto.auth.UserAuthDTO;
import ru.spaceouter.infoscan.dto.view.ConfirmDTO;
import ru.spaceouter.infoscan.dto.view.EmailDTO;
import ru.spaceouter.infoscan.dto.view.UpdatePasswordDTO;
import ru.spaceouter.infoscan.exceptions.InvalidAuthenticationException;
import ru.spaceouter.infoscan.exceptions.UnauthorizedException;
import ru.spaceouter.infoscan.exceptions.WrongArgumentsException;
import ru.spaceouter.infoscan.rest.RestControllerWithAuthorization;
import ru.spaceouter.infoscan.services.transactional.AuthService;
import ru.spaceouter.infoscan.services.transactional.UpdateService;

import javax.validation.Valid;

/**
 * @author danil
 * @date 20.04.19
 */
@RestController
@RequestMapping("/api/update")
public class RESTUpdateController extends RestControllerWithAuthorization<UserAuthDTO> {

    private final UpdateService updateService;

    public RESTUpdateController(AuthService<UserAuthDTO> authService,
                                UpdateService updateService) {
        super(authService);
        this.updateService = updateService;
    }

    @PostMapping(path = "/email")
    public ResponseEntity<?> updateEmail(@Valid@RequestBody EmailDTO emailDTO,
                                         BindingResult bindingResult,
                                         @CookieValue(name = "auth_token", required = false) String token)
            throws UnauthorizedException, InvalidAuthenticationException, WrongArgumentsException {

        if(bindingResult.hasErrors())
            throw new WrongArgumentsException();

        updateService.updateEmail(
                getAuthDataByToken(token).getUserId(),
                emailDTO.getEmail());
        return created();
    }

    @PutMapping(path = "/email")
    public ResponseEntity<?> updateEmailConfirm(@Valid @RequestBody ConfirmDTO confirmDTO,
                                                BindingResult bindingResult) throws WrongArgumentsException {

        if(bindingResult.hasErrors())
            throw new WrongArgumentsException();

        updateService.confirmEmailUpdating(confirmDTO.getUuid());
        return accepted();
    }

    @PutMapping(path = "/pass")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody UpdatePasswordDTO updatePasswordDTO,
                                            BindingResult bindingResult,
                                            @CookieValue(name = "auth_token", required = false) String token)
            throws UnauthorizedException, InvalidAuthenticationException, WrongArgumentsException {

        if(bindingResult.hasErrors())
            throw new WrongArgumentsException();

        updateService.updatePassword(
                getAuthDataByToken(token).getUserId(),
                updatePasswordDTO.getPassword(),
                updatePasswordDTO.getNewPassword());
        return accepted();
    }

}
