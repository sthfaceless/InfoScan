package ru.sthfaceless.qscan.services.transactional;

import ru.spaceouter.infoscan.dto.support.CreateQuestionDTO;

/**
 * @author danil
 * @date 20.04.19
 */
public interface SupportService {

    void createQuestion(long userId, CreateQuestionDTO createQuestionDTO);

}
