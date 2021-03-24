package ru.sthfaceless.qscan.rest.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spaceouter.infoscan.dto.auth.RestoreDTO;
import ru.spaceouter.infoscan.dto.auth.UserAuthDTO;
import ru.spaceouter.infoscan.dto.user.CreateUserDTO;
import ru.spaceouter.infoscan.dto.view.ConfirmDTO;
import ru.spaceouter.infoscan.dto.view.EmailDTO;
import ru.spaceouter.infoscan.exceptions.WrongArgumentsException;
import ru.spaceouter.infoscan.rest.RestControllerWithAuthorization;
import ru.spaceouter.infoscan.services.transactional.AuthService;
import ru.spaceouter.infoscan.services.transactional.UserService;

import javax.validation.Valid;

/**
 * @author danil
 * @date 20.04.19
 */
@RestController
@RequestMapping("/api/user")
public class RESTUserController extends RestControllerWithAuthorization<UserAuthDTO> {

    private final UserService userService;

    public RESTUserController(AuthService<UserAuthDTO> authService,
                              UserService userService) {
        super(authService);
        this.userService = userService;
    }

    @PostMapping(path = "/reg")
    public ResponseEntity<?> reg(@Valid @RequestBody CreateUserDTO createUserDTO,
                                 BindingResult bindingResult) throws WrongArgumentsException {

        if(bindingResult.hasErrors())
            throw new WrongArgumentsException();

        userService.createUser(createUserDTO);
        return created();
    }

    @GetMapping("/exist")
    public ResponseEntity<?> exist(@RequestParam(name = "username", required = false) String username,
                                   @RequestParam(name = "email", required = false) String email){

        if(!StringUtils.isEmpty(username) && userService.existUsername(username))
            return new ResponseEntity<>(HttpStatus.IM_USED);
        else if(!StringUtils.isEmpty(email) && userService.existEmail(email))
            return new ResponseEntity<>(HttpStatus.IM_USED);
        else
            return ok();
    }

    @PutMapping(path = "/reg")
    public ResponseEntity<?> regConfirm(@Valid @RequestBody ConfirmDTO confirmDTO,
                                        BindingResult bindingResult) throws WrongArgumentsException {

        if(bindingResult.hasErrors())
            throw new WrongArgumentsException();

        userService.activateUser(confirmDTO.getUuid());
        return accepted();
    }

    @PostMapping(path = "/restore")
    public ResponseEntity<?> restore(@Valid @RequestBody EmailDTO emailDTO,
                                     BindingResult bindingResult) throws WrongArgumentsException {

        if(bindingResult.hasErrors())
            throw new WrongArgumentsException();

        userService.restore(emailDTO.getEmail());
        return created();
    }

    @PutMapping(path = "/restore")
    public ResponseEntity<?> restoreConfirm(@Valid @RequestBody RestoreDTO restoreDTO,
                                            BindingResult bindingResult) throws WrongArgumentsException {

        if(bindingResult.hasErrors())
            throw new WrongArgumentsException();

        userService.confirmRestore(restoreDTO);
        return accepted();
    }


}
