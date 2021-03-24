package ru.spaceouter.infoscan.dto.coins;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author danil
 * @date 25.04.19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoinsDTO {

    private long user;
    private long amount;

}
