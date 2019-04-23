package ru.spaceouter.infoscan.services.transactional.implementations;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.spaceouter.infoscan.model.ActivateCustomDAO;
import ru.spaceouter.infoscan.model.AuthSpringDAO;
import ru.spaceouter.infoscan.model.entities.user.UserEntity;
import ru.spaceouter.infoscan.model.hibernate.ProxyDAO;
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

    private final ActivateCustomDAO activateCustomDAO;
    private final AuthSpringDAO authSpringDAO;
    private final ProxyDAO proxyDAO;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final EmailService emailService;
    private final TokenService tokenService;

    @Override
    public void updateEmail(long userId, String email) {

        String token = tokenService.nextToken();
        activateCustomDAO.setConfirmEmailToken(userId, token, email);

        emailService.sendConfirmEmailMessage(email, token);
    }

    @Override
    public boolean confirmEmailUpdating(String uuid) {

       return activateCustomDAO.confirmEmail(uuid);
    }

    @Override
    public boolean updatePassword(long userId, String pass, String newPass) {

        UserEntity user = proxyDAO.getUserProxy(userId);

        if(!bCryptPasswordEncoder.matches(pass, authSpringDAO.getPasswordByUser(user)))
            return false;

        authSpringDAO.updatePassword(pass, user);
        return true;
    }
}
