package ru.spaceouter.infoscan.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author danil
 * @date 23.04.19
 */
@Data
@AllArgsConstructor
public class SocialNetworkWithOrderDTO {

    private final long socialNetworkId;
    private final long orderId;

}
