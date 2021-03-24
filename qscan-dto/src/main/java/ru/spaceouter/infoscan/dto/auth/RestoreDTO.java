package ru.spaceouter.infoscan.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.spaceouter.infoscan.validation.Password;

import javax.validation.constraints.NotNull;

/**
 * @author danil
 * @date 22.04.19
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestoreDTO {

    @NotNull
    private String uuid;

    @Password
    private String password;

}
