package ru.spaceouter.infoscan.dto.auth;

import lombok.AllArgsConstructor;

/**
 * @author danil
 * @date 21.04.19
 */
@AllArgsConstructor
public enum Role {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String name;

    @Override
    public String toString() {
        return this.name;
    }

}
