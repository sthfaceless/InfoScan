package ru.sthfaceless.qscan.dto.view;

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
public class PageableRequest {

    @NotNull
    private int start;

}
