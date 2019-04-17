package ru.spaceouter.infoscan.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author danil
 * @date 14.04.19
 */
@Getter
@AllArgsConstructor
public class AuthResultDTO {

    private final long userId;
    private final boolean success;
    private final String token;

}
