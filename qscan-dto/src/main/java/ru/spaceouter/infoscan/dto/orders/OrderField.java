package ru.spaceouter.infoscan.dto.orders;

import lombok.AllArgsConstructor;

/**
 * @author danil
 * @date 23.04.19
 */
@AllArgsConstructor
public enum OrderField {

    FIRST_NAME("FIRST_NAME"),
    LAST_NAME("LAST_NAME"),
    PATRONYMIC("PATRONYMIC"),
    IP("IP"),
    PHONE("PHONE"),
    PICTURE("PICTURE"),
    PSEUDONAME("PSEUDONAME"),
    EMAIL("EMAIL"),
    ALTERNATE("ALTERNATE"),

    SOCIAL_NETWORKS("SOCIAL_NETWORKS");

    private final String field;

    @Override
    public String toString(){
        return this.field;
    }

}
