package ru.spaceouter.infoscan.dto.orders;

import lombok.AllArgsConstructor;

/**
 * @author danil
 * @date 20.04.19
 */
@AllArgsConstructor
public enum OrderStatus {

    ACCEPTED("ACCEPTED"),
    PROCESSED("PROCESSED"),
    FINISHED("FINISHED");

    private final String name;

    @Override
    public String toString() {
        return this.name;
    }

}
