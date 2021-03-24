package ru.sthfaceless.qscan.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.spaceouter.infoscan.validation.Password;
import ru.spaceouter.infoscan.validation.Username;

/**
 * @author danil
 * @date 20.04.19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthCredentialsDTO {

    @Username
    private String username;

    @Password
    private String password;

}
