package ru.spaceouter.infoscan.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author danil
 * @date 20.04.19
 */
@Getter
@AllArgsConstructor
public class FullOrderDTO {

    private final long id;

    private final String firstName;
    private final String lastName;
    private final String patronymic;

    private final String ip;
    private final String phone;
    private final String picture;

    private final String pseudoName;
    private final String email;

    @Setter
    private List<SocialNetworkDTO> socialNetworks;

    private String alternate;

    public FullOrderDTO(long id, String firstName, String lastName, String patronymic, String ip, String phone, String picture, String pseudoName, String email, String alternate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.ip = ip;
        this.phone = phone;
        this.picture = picture;
        this.pseudoName = pseudoName;
        this.email = email;
        this.alternate = alternate;
    }
}
