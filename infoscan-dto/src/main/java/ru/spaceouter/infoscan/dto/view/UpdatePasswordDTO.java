package ru.spaceouter.infoscan.dto.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.spaceouter.infoscan.validation.Password;

/**
 * @author danil
 * @date 20.04.19
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordDTO {

    @Password
    private String password;
    @Password
    private String newPassword;

}
