package ru.spaceouter.infoscan.services.transactional;

import ru.spaceouter.infoscan.dto.view.CreateQuestionDTO;

/**
 * @author danil
 * @date 20.04.19
 */
public interface SupportService {

    void createQuestion(long userId, CreateQuestionDTO createQuestionDTO);

}
