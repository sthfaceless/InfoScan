package ru.spaceouter.infoscan.dto.view;

import lombok.Getter;
import ru.spaceouter.infoscan.dto.orders.OrderStatus;

import java.util.Date;

/**
 * @author danil
 * @date 20.04.19
 */
@Getter
public class ViewOrderDTO {

    private final long id;
    private final String username;
    private final Date date;
    private final OrderStatus orderStatus;

    public ViewOrderDTO(long id, String firstName, String lastName, String patronymic, String pseudoName,
                        Date date, OrderStatus orderStatus) {
        this.id = id;

        this.username = (firstName == null ? "" : firstName) + " " +
                (lastName == null ? "" : lastName) + " " +
                (patronymic == null ? "" : patronymic) + " " +
                "(" + pseudoName + ")";

        this.date = date;
        this.orderStatus = orderStatus;
    }
}
