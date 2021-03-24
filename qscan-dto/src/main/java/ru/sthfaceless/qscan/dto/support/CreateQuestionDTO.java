package ru.sthfaceless.qscan.dto.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author danil
 * @date 20.04.19
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateQuestionDTO {

    @NotNull
    private String question;

}
