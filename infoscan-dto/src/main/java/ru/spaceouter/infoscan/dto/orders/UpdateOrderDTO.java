package ru.spaceouter.infoscan.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author danil
 * @date 23.04.19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderDTO {

    private long orderId;
    private OrderField field;
    private String value;

}
