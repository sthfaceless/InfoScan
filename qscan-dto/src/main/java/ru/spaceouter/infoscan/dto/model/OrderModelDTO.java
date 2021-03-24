package ru.spaceouter.infoscan.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author danil
 * @date 25.04.19
 */
@Data
@AllArgsConstructor
public class OrderModelDTO {

    private final long orderId;
    private final long orderInformationId;

}
