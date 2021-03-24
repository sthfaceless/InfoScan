package ru.spaceouter.infoscan.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.spaceouter.infoscan.model.entities.support.SupportQuestion;

/**
 * @author danil
 * @date 22.04.19
 */
@Repository
public interface SupportSpringDAO extends CrudRepository<SupportQuestion, Long> {
}
