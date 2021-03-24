package ru.sthfaceless.qscan.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author danil
 * @date 22.04.19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokenDTO {

    private String token;

}
