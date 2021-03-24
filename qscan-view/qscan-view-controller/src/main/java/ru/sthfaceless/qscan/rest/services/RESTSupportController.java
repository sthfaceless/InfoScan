package ru.sthfaceless.qscan.rest.services;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spaceouter.infoscan.dto.auth.UserAuthDTO;
import ru.spaceouter.infoscan.dto.support.CreateQuestionDTO;
import ru.spaceouter.infoscan.exceptions.InvalidAuthenticationException;
import ru.spaceouter.infoscan.exceptions.UnauthorizedException;
import ru.spaceouter.infoscan.exceptions.WrongArgumentsException;
import ru.spaceouter.infoscan.rest.RestControllerWithAuthorization;
import ru.spaceouter.infoscan.services.transactional.AuthService;
import ru.spaceouter.infoscan.services.transactional.SupportService;

import javax.validation.Valid;

/**
 * @author danil
 * @date 20.04.19
 */
@RestController
@RequestMapping("/api/support")
public class RESTSupportController extends RestControllerWithAuthorization<UserAuthDTO> {

    private final SupportService supportService;

    public RESTSupportController(AuthService<UserAuthDTO> authService,
                                 SupportService supportService) {
        super(authService);
        this.supportService = supportService;
    }

    @PostMapping
    public ResponseEntity<?> createQuestion(@Valid @RequestBody CreateQuestionDTO createQuestionDTO,
                                            BindingResult bindingResult,
                                            @CookieValue(name = "auth_token", required = false) String token)
            throws UnauthorizedException, InvalidAuthenticationException, WrongArgumentsException {

        if(bindingResult.hasErrors())
            throw new WrongArgumentsException();

        supportService.createQuestion(
                getAuthDataByToken(token).getUserId(),
                createQuestionDTO);
        return created();
    }


}
