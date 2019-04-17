package ru.spaceouter.infoscan.coins;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author danil
 * @date 14.04.19
 */

@Getter
@AllArgsConstructor
public class UserCoinStatusDTO {

    private final long userId;
    private final int value;

}
