package ru.spaceouter.infoscan.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author danil
 * @date 23.04.19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialNetworkDTO {

    private long id;
    private SocialNetworkType type;
    private String link;

}
