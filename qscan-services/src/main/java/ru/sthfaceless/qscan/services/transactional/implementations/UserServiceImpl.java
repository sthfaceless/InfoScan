package ru.sthfaceless.qscan.services.transactional.implementations;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.spaceouter.infoscan.dto.auth.RestoreDTO;
import ru.spaceouter.infoscan.dto.auth.Role;
import ru.spaceouter.infoscan.dto.user.CreateUserDTO;
import ru.spaceouter.infoscan.model.ActivateSpringDAO;
import ru.spaceouter.infoscan.model.UserSpringDAO;
import ru.spaceouter.infoscan.model.entities.coins.CoinsEntity;
import ru.spaceouter.infoscan.model.entities.user.ActivationEntity;
import ru.spaceouter.infoscan.model.entities.user.AuthEntity;
import ru.spaceouter.infoscan.model.entities.user.RoleEntity;
import ru.spaceouter.infoscan.model.entities.user.UserEntity;
import ru.spaceouter.infoscan.services.ParametersValidator;
import ru.spaceouter.infoscan.services.TokenService;
import ru.spaceouter.infoscan.services.transactional.EmailService;
import ru.spaceouter.infoscan.services.transactional.UserService;

import java.util.Calendar;
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
    private final ActivateSpringDAO activateSpringDAO;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenService tokenService;
    private final ParametersValidator parametersValidator;

    @Override
    public void createUser(CreateUserDTO createUserDTO){

        final UserEntity userEntity = new UserEntity(
                createUserDTO.getUsername(),
                new Date());

        final RoleEntity roleEntity = new RoleEntity(Role.USER);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 30);
        Date expiredDate = new Date(calendar.getTimeInMillis());

        final AuthEntity authEntity = new AuthEntity(
                createUserDTO.getUsername(),
                bCryptPasswordEncoder.encode(createUserDTO.getPassword()),
                createUserDTO.getEmail(),
                tokenService.nextToken(),
                expiredDate,
                false,
                roleEntity
        );
        authEntity.setUser(userEntity);

        ActivationEntity activation = new ActivationEntity(authEntity);
        activation.setActivateAccount(tokenService.nextToken());
        authEntity.setActivation(activation);

        roleEntity.setAuth(authEntity);
        userEntity.setAuth(authEntity);

        CoinsEntity coinsEntity = new CoinsEntity(0, userEntity);
        userEntity.setCoins(coinsEntity);

        userSpringDAO.save(userEntity);

        emailService.sendActivateAccountMessage(createUserDTO.getEmail(), activation.getActivateAccount());
    }

    @Override
    public void activateUser(String uuid) {
        activateSpringDAO.activateAccount(uuid);
    }

    @Override
    public void restore(String email){

        String confirmRestore = tokenService.nextToken();
        activateSpringDAO.setConfirmPasswordToken(confirmRestore, email);

        emailService.sendConfirmPasswordRestoreMessage(email, confirmRestore);

    }

    @Override
    public void confirmRestore(RestoreDTO restoreDTO){

        activateSpringDAO.confirmPassword(restoreDTO.getUuid(),
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
