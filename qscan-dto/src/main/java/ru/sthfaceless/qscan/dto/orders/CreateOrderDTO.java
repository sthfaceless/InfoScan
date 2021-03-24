package ru.sthfaceless.qscan.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author danil
 * @date 25.04.19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDTO {

    private String firstName;
    private String lastName;
    private String patronymic;

    private String ip;
    private String phone;
    private String picture;
    private Gender gender;

    private String pseudoName;
    private String email;

    private List<SocialNetworkDTO> socialNetworks;

    private String alternate;

    public CreateOrderDTO(String firstName, String lastName, String patronymic, String ip, String phone, String picture, String pseudoName, String email, String alternate, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.ip = ip;
        this.phone = phone;
        this.picture = picture;
        this.pseudoName = pseudoName;
        this.email = email;
        this.alternate = alternate;
        this.gender = gender;
    }
}
