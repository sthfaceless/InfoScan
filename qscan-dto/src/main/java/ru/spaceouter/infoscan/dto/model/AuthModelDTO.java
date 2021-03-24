package ru.spaceouter.infoscan.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author danil
 * @date 22.04.19
 */
@Getter
@AllArgsConstructor
public class AuthModelDTO {

    private final long authId;
    private final String username;
    private final String password;
    private final String token;
    private final boolean active;
    private final long userId;
}
