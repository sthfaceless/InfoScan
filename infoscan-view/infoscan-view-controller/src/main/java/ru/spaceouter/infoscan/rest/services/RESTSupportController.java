package ru.spaceouter.infoscan.rest.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spaceouter.infoscan.dto.auth.UserAuthDTO;
import ru.spaceouter.infoscan.dto.view.CreateQuestionDTO;
import ru.spaceouter.infoscan.exceptions.UnauthorizedException;
import ru.spaceouter.infoscan.rest.RestControllerWithAuthorization;
import ru.spaceouter.infoscan.services.transactional.AuthService;
import ru.spaceouter.infoscan.services.transactional.SupportService;

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
    public ResponseEntity<?> createQuestion(@RequestBody CreateQuestionDTO createQuestionDTO,
                                            @CookieValue(name = "token", required = false) String token)
            throws UnauthorizedException {

        supportService.createQuestion(
                getAuthDataByToken(token).getUserId(),
                createQuestionDTO);
        return created();
    }


}
