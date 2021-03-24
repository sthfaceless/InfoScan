package ru.spaceouter.infoscan.dto.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author danil
 * @date 24.04.19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmDTO {

    @NotNull
    private String uuid;

}
