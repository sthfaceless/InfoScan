package ru.sthfaceless.qscan.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.spaceouter.infoscan.model.entities.user.UserEntity;

/**
 * @author danil
 * @date 21.04.19
 */
@Repository
public interface UserSpringDAO extends CrudRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

}
