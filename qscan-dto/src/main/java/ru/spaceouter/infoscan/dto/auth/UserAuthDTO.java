package ru.spaceouter.infoscan.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author danil
 * @date 21.04.19
 */
@Getter
@AllArgsConstructor
public class UserAuthDTO {

    private final long userId;
    private final String username;
    private final String token;

}
