package ru.spaceouter.infoscan.services.transactional;

import ru.spaceouter.infoscan.dto.view.CreateUserDTO;
import ru.spaceouter.infoscan.dto.view.RestoreDTO;
import ru.spaceouter.infoscan.dto.view.StartRestoreDTO;

/**
 * @author danil
 * @date 20.04.19
 */
public interface UserService {

    void createUser(CreateUserDTO createUserDTO);

    boolean activateUser(String uuid);

    void restore(StartRestoreDTO startRestoreDTO);

    boolean confirmRestore(RestoreDTO restoreDTO);

    boolean existEmail(String email);

    boolean existUsername(String username);

}
