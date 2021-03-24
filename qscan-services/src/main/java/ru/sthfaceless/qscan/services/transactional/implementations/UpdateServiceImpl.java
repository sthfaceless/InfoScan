package ru.sthfaceless.qscan.services.transactional.implementations;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.spaceouter.infoscan.model.ActivateSpringDAO;
import ru.spaceouter.infoscan.model.AuthSpringDAO;
import ru.spaceouter.infoscan.model.CommonDAO;
import ru.spaceouter.infoscan.model.entities.user.UserEntity;
import ru.spaceouter.infoscan.services.TokenService;
import ru.spaceouter.infoscan.services.transactional.EmailService;
import ru.spaceouter.infoscan.services.transactional.UpdateService;

/**
 * @author danil
 * @date 20.04.19
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
@AllArgsConstructor
public class UpdateServiceImpl implements UpdateService {

    private final ActivateSpringDAO activateSpringDAO;
    private final AuthSpringDAO authSpringDAO;
    private final CommonDAO commonDAO;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final EmailService emailService;
    private final TokenService tokenService;

    @Override
    public void updateEmail(long userId, String email) {

        String token = tokenService.nextToken();
        activateSpringDAO.setConfirmEmailToken(commonDAO.getEntityProxy(UserEntity.class, userId), token, email);

        emailService.sendConfirmEmailMessage(email, token);
    }

    @Override
    public void confirmEmailUpdating(String uuid) {

        String email = activateSpringDAO.getTempEmail(uuid);
        if(email != null)
            activateSpringDAO.confirmEmail(uuid, email);
    }

    @Override
    public void updatePassword(long userId, String pass, String newPass) {

        UserEntity user = commonDAO.getEntityProxy(UserEntity.class, userId);

        if(!bCryptPasswordEncoder.matches(pass, authSpringDAO.getPasswordByUser(user)))
            return;

        authSpringDAO.updatePassword(bCryptPasswordEncoder.encode(newPass), user);
    }
}
