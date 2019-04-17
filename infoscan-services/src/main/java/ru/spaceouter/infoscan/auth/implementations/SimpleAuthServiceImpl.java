package ru.spaceouter.infoscan.auth.implementations;

import org.springframework.stereotype.Service;
import ru.spaceouter.infoscan.auth.AuthResultDTO;
import ru.spaceouter.infoscan.auth.SimpleAuthService;

import java.util.UUID;

/**
 * @author danil
 * @date 14.04.19
 */

@Service
public class SimpleAuthServiceImpl implements SimpleAuthService {

    @Override
    public AuthResultDTO authenticateUser(String log, String pass) {
        return "root".equals(log) ?
                new AuthResultDTO(1, true, UUID.randomUUID().toString())
                : new AuthResultDTO(-1, false, "");
    }

}
