package ru.sthfaceless.qscan.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author danil
 * @date 20.04.19
 */
@Data
@AllArgsConstructor
public class FullOrderDTO {

    private final long id;

    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final String username;

    private final String ip;
    private final String phone;
    private final String picture;

    private final String pseudoName;
    private final String email;

    private final OrderStatus orderStatus;
    private final Date date;

    private List<SocialNetworkDTO> socialNetworks;

    private String alternate;

    public FullOrderDTO(long id, String firstName, String lastName, String patronymic, String ip, String phone, String picture, String pseudoName, String email, String alternate,
                        Date date, OrderStatus status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.username = (firstName == null ? "" : firstName) + " " +
                (lastName == null ? "" : lastName) + " " +
                (patronymic == null ? "" : patronymic) + " " +
                "(" + pseudoName + ")";;
        this.ip = ip;
        this.phone = phone;
        this.picture = picture;
        this.pseudoName = pseudoName;
        this.email = email;
        this.alternate = alternate;
        this.date = date;
        this.orderStatus = status;
    }
}
