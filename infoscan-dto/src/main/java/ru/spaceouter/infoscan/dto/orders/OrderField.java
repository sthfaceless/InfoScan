package ru.spaceouter.infoscan.dto.orders;

import lombok.AllArgsConstructor;

/**
 * @author danil
 * @date 23.04.19
 */
@AllArgsConstructor
public enum OrderField {

    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
    PATRONYMIC("patronymic"),
    IP("ip"),
    PHONE("phone"),
    PICTURE("picture"),
    PSEUDONAME("pseudoName"),
    ENAIL("email"),
    ALTERNATE("alternate"),

    SOCIAL_NETWORKS("social_networks");

    private final String field;

    @Override
    public String toString(){
        return this.field;
    }

}
