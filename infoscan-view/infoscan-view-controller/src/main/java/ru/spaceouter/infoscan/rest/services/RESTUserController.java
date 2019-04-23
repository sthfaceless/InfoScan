package ru.spaceouter.infoscan.rest.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.spaceouter.infoscan.dto.auth.UserAuthDTO;
import ru.spaceouter.infoscan.dto.view.CreateUserDTO;
import ru.spaceouter.infoscan.dto.view.RestoreDTO;
import ru.spaceouter.infoscan.dto.view.StartRestoreDTO;
import ru.spaceouter.infoscan.rest.RestControllerWithAuthorization;
import ru.spaceouter.infoscan.services.transactional.AuthService;
import ru.spaceouter.infoscan.services.transactional.UserService;

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
    public ResponseEntity<?> reg(@RequestBody CreateUserDTO createUserDTO){

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

    @PutMapping(path = "/reg/{uuid}")
    public ResponseEntity<?> regConfirm(@PathVariable("uuid") String uuid){

        userService.activateUser(uuid);
        return accepted();
    }

    @PostMapping(path = "/restore")
    public ResponseEntity<?> restore(@RequestBody StartRestoreDTO startRestoreDTO) {

        userService.restore(startRestoreDTO);
        return created();
    }

    @PutMapping(path = "/restore/")
    public ResponseEntity<?> restoreConfirm(@RequestBody RestoreDTO restoreDTO){

        userService.confirmRestore(restoreDTO);
        return accepted();
    }


}
