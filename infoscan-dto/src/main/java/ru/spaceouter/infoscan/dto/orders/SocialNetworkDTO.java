package ru.spaceouter.infoscan.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author danil
 * @date 23.04.19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialNetworkDTO {

    private long orderId;
    private SocialNetworkType type;

    @NotNull
    private String link;

}
