package ru.spaceouter.infoscan.services.transactional.implementations;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.spaceouter.infoscan.dto.auth.Role;
import ru.spaceouter.infoscan.dto.view.CreateUserDTO;
import ru.spaceouter.infoscan.dto.view.RestoreDTO;
import ru.spaceouter.infoscan.dto.view.StartRestoreDTO;
import ru.spaceouter.infoscan.model.ActivateCustomDAO;
import ru.spaceouter.infoscan.model.UserSpringDAO;
import ru.spaceouter.infoscan.model.entities.user.ActivationEntity;
import ru.spaceouter.infoscan.model.entities.user.AuthEntity;
import ru.spaceouter.infoscan.model.entities.user.RoleEntity;
import ru.spaceouter.infoscan.model.entities.user.UserEntity;
import ru.spaceouter.infoscan.services.TokenService;
import ru.spaceouter.infoscan.services.transactional.EmailService;
import ru.spaceouter.infoscan.services.transactional.UserService;

import java.util.Date;

/**
 * @author danil
 * @date 20.04.19
 */
@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

    private EmailService emailService;

    private final UserSpringDAO userSpringDAO;
    private final ActivateCustomDAO activateCustomDAO;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenService tokenService;

    @Override
    public void createUser(CreateUserDTO createUserDTO){

        final UserEntity userEntity = new UserEntity(
                createUserDTO.getLogin(),
                createUserDTO.getEmail(),
                new Date());

        final RoleEntity roleEntity = new RoleEntity(Role.USER);

        final AuthEntity authEntity = new AuthEntity(
                createUserDTO.getLogin(),
                bCryptPasswordEncoder.encode(createUserDTO.getPassword()),
                tokenService.nextToken(),
                false,
                roleEntity
        );
        authEntity.setUser(userEntity);

        ActivationEntity activation = new ActivationEntity(authEntity);
        activation.setActivateAccount(tokenService.nextToken());
        authEntity.setActivation(activation);

        roleEntity.setAuth(authEntity);
        userEntity.setAuth(authEntity);

        userSpringDAO.save(userEntity);

        emailService.sendActivateAccountMessage(createUserDTO.getEmail(), activation.getActivateAccount());
    }

    @Override
    public boolean activateUser(String uuid) {
        return activateCustomDAO.activateAccount(uuid);
    }

    @Override
    public void restore(StartRestoreDTO startRestoreDTO){

        String confirmRestore = tokenService.nextToken();
        activateCustomDAO.setConfirmPasswordToken(confirmRestore, startRestoreDTO.getEmail());

        emailService.sendConfirmPasswordRestoreMessage(startRestoreDTO.getEmail(), confirmRestore);

    }

    @Override
    public boolean confirmRestore(RestoreDTO restoreDTO) {
       return activateCustomDAO.confirmPassword(restoreDTO.getUuid(),
               bCryptPasswordEncoder.encode(restoreDTO.getPassword()));
    }

    @Override
    public boolean existEmail(String email) {
        return userSpringDAO.existsByEmail(email);
    }

    @Override
    public boolean existUsername(String username) {
        return userSpringDAO.existsByUsername(username);
    }
}
