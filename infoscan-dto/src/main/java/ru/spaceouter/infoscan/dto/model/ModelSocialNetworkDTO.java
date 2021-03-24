package ru.spaceouter.infoscan.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.spaceouter.infoscan.dto.orders.SocialNetworkType;

/**
 * @author danil
 * @date 23.04.19
 */
@Data
@AllArgsConstructor
public class ModelSocialNetworkDTO {

    private final long id;
    private final long orderId;
    private final SocialNetworkType type;

}
