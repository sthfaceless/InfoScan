package ru.spaceouter.infoscan.services.transactional.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.spaceouter.infoscan.dto.support.CreateQuestionDTO;
import ru.spaceouter.infoscan.model.CommonDAO;
import ru.spaceouter.infoscan.model.SupportSpringDAO;
import ru.spaceouter.infoscan.model.entities.support.SupportQuestion;
import ru.spaceouter.infoscan.model.entities.user.UserEntity;
import ru.spaceouter.infoscan.services.transactional.SupportService;

/**
 * @author danil
 * @date 20.04.19
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
@AllArgsConstructor
public class SupportServiceImpl implements SupportService {

    private final SupportSpringDAO supportSpringDAO;
    private final CommonDAO commonDAO;

    @Override
    public void createQuestion(long userId, CreateQuestionDTO createQuestionDTO) {

        SupportQuestion supportQuestion = new SupportQuestion(
                createQuestionDTO.getQuestion());
        supportQuestion.setUser(commonDAO.getEntityProxy(UserEntity.class, userId));

        supportSpringDAO.save(supportQuestion);
    }

}
