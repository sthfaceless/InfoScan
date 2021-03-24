package ru.spaceouter.infoscan.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.spaceouter.infoscan.validation.Email;
import ru.spaceouter.infoscan.validation.Password;
import ru.spaceouter.infoscan.validation.Username;

/**
 * @author danil
 * @date 20.04.19
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {

    @Username
    private String username;

    @Email
    private String email;

    @Password
    private String password;

}
