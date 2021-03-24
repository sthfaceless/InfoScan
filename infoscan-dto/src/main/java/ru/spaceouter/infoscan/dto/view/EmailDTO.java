package ru.spaceouter.infoscan.dto.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.spaceouter.infoscan.validation.Email;

/**
 * @author danil
 * @date 25.04.19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {

    @Email
    private String email;

}
