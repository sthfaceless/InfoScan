package ru.spaceouter.infoscan.rest.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spaceouter.infoscan.dto.auth.UserAuthDTO;
import ru.spaceouter.infoscan.dto.view.UpdateEmailDTO;
import ru.spaceouter.infoscan.dto.view.UpdatePasswordDTO;
import ru.spaceouter.infoscan.exceptions.UnauthorizedException;
import ru.spaceouter.infoscan.rest.RestControllerWithAuthorization;
import ru.spaceouter.infoscan.services.transactional.AuthService;
import ru.spaceouter.infoscan.services.transactional.UpdateService;

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

    @PostMapping
    @RequestMapping(path = "/email")
    public ResponseEntity<?> updateEmail(@RequestBody UpdateEmailDTO updateEmailDTO,
                                         @CookieValue(name = "token", required = false) String token)
            throws UnauthorizedException {

        updateService.updateEmail(
                getAuthDataByToken(token).getUserId(),
                updateEmailDTO.getEmail());
        return created();
    }

    @PutMapping
    @RequestMapping(path = "/email/{uuid}")
    public ResponseEntity<?> updateEmailConfirm(@PathVariable("uuid") String uuid){

        updateService.confirmEmailUpdating(uuid);
        return accepted();
    }

    @PutMapping
    @RequestMapping(path = "/pass")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO,
                                            @CookieValue(name = "token", required = false) String token)
            throws UnauthorizedException{

        updateService.updatePassword(
                getAuthDataByToken(token).getUserId(),
                updatePasswordDTO.getPassword(),
                updatePasswordDTO.getNewPassword());
        return accepted();
    }

}
