package ru.spaceouter.infoscan.auth;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author danil
 * @date 14.04.19
 */

@RestController
@AllArgsConstructor
public class SimpleAuthController {

    private final SimpleAuthService simpleAuthService;

    @PostMapping(path = "/login")
    public AuthResultDTO auth(@RequestParam Map<String, String> params){

        return simpleAuthService.authenticateUser(params.getOrDefault("log", null), params.getOrDefault("pass", null));
    }

}
