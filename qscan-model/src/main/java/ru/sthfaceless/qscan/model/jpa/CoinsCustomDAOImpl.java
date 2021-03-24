package ru.sthfaceless.qscan.model.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.spaceouter.infoscan.dto.coins.PaymentDTO;
import ru.spaceouter.infoscan.model.CoinsCustomDAO;
import ru.spaceouter.infoscan.model.entities.coins.CoinsEntity;
import ru.spaceouter.infoscan.model.entities.coins.CoinsPaymentEntity;
import ru.spaceouter.infoscan.model.entities.user.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * @author danil
 * @date 22.04.19
 */
@Repository
@Transactional
public class CoinsCustomDAOImpl implements CoinsCustomDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<PaymentDTO> getPayments(long userId, int start, int size, String order, String type) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PaymentDTO> query = cb.createQuery(PaymentDTO.class);
        Root<CoinsPaymentEntity> payment = query.from(CoinsPaymentEntity.class);

        Join<CoinsPaymentEntity, CoinsEntity> coins = payment.join("coins", JoinType.LEFT);

        query.select(cb.construct(PaymentDTO.class, payment.get("coinsPaymentId"), payment.get("quantity"),
                payment.get("date"), payment.get("service")));
        query.where(cb.equal(coins.get("user"), em.getReference(UserEntity.class, userId)));

        Path orderField;
        if("quantity".equals(order)) orderField = payment.get("quantity");
        else if("service".equals(order)) orderField = payment.get("service");
        else orderField = payment.get("date");

        query.orderBy("0".equals(type) ? cb.asc(orderField) : cb.desc(orderField));

        return em.createQuery(query).setFirstResult(start).setMaxResults(size+1).getResultList();
    }
}
