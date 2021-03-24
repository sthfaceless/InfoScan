package ru.sthfaceless.qscan.model.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.spaceouter.infoscan.model.CommonDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author danil
 * @date 22.04.19
 */
@Repository
@Transactional(readOnly = true)
public class CommonDAOImpl implements CommonDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public <T> T getEntityProxy(Class<T> clazz, long id) {
        return em.getReference(clazz, id);
    }

    @Override
    public void persistEntity(Object object){
        em.persist(object);
    }
}
