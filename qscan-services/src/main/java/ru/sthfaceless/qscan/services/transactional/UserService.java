package ru.sthfaceless.qscan.services.transactional;

import ru.spaceouter.infoscan.dto.auth.RestoreDTO;
import ru.spaceouter.infoscan.dto.user.CreateUserDTO;

/**
 * @author danil
 * @date 20.04.19
 */
public interface UserService {

    void createUser(CreateUserDTO createUserDTO);

    void activateUser(String uuid);

    void restore(String email);

    void confirmRestore(RestoreDTO restoreDTO);

    boolean existEmail(String email);

    boolean existUsername(String username);

}
