package ru.spaceouter.infoscan.model.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.spaceouter.infoscan.dto.model.ModelSocialNetworkDTO;
import ru.spaceouter.infoscan.dto.orders.FullOrderDTO;
import ru.spaceouter.infoscan.dto.orders.SocialNetworkDTO;
import ru.spaceouter.infoscan.dto.orders.ViewOrderDTO;
import ru.spaceouter.infoscan.exceptions.NotExistException;
import ru.spaceouter.infoscan.model.OrdersCustomDAO;
import ru.spaceouter.infoscan.model.OrdersSpringDAO;
import ru.spaceouter.infoscan.model.entities.orders.OrderEntity;
import ru.spaceouter.infoscan.model.entities.orders.OrderInformation;
import ru.spaceouter.infoscan.model.entities.orders.SocialNetwork;
import ru.spaceouter.infoscan.model.entities.user.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * @author danil
 * @date 23.04.19
 */
@Repository
@Transactional
public class OrdersCustomDAOImpl implements OrdersCustomDAO{

    @PersistenceContext
    private EntityManager em;

    private OrdersSpringDAO ordersSpringDAO;

    public OrdersCustomDAOImpl(OrdersSpringDAO ordersSpringDAO) {
        this.ordersSpringDAO = ordersSpringDAO;
    }

    @Override
    public List<ViewOrderDTO> getOrdersByUser(long userId, String key, int start, int size, String ordering, String type) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ViewOrderDTO> query = cb.createQuery(ViewOrderDTO.class);
        Root<OrderEntity> order = query.from(OrderEntity.class);

        Join<OrderEntity, OrderInformation> info = order.join("orderInformation", JoinType.LEFT);

        query.select(cb.construct(ViewOrderDTO.class, order.get("orderId"),
                info.get("firstName"), info.get("lastName"), info.get("patronymic"), info.get("pseudoName"),
                order.get("createDate"), order.get("status")));

        if(!StringUtils.isEmpty(query)) {
            key = '%' + key + '%';
            query.where(cb.or(cb.like(info.get("firstName"), key), cb.like(info.get("lastName"), key),
                    cb.like(info.get("pseudoName"), key), cb.like(info.get("email"), key)));
        }

        Path orderField;
        if("status".equals(ordering)) orderField = order.get("status");
        else orderField = order.get("createDate");

        query.orderBy("0".equals(type) ? cb.asc(orderField) : cb.desc(orderField));

        return em.createQuery(query).setFirstResult(start).setMaxResults(size+1).getResultList();
    }

    @Override
    public FullOrderDTO getFullOrder(long orderId, long userId) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FullOrderDTO> query = cb.createQuery(FullOrderDTO.class);
        Root<OrderEntity> order = query.from(OrderEntity.class);

        Join<OrderEntity, OrderInformation> info = order.join("orderInformation", JoinType.LEFT);

        query.select(cb.construct(FullOrderDTO.class, order.get("orderId"),
                info.get("firstName"), info.get("lastName"), info.get("patronymic"),
                info.get("ip"), info.get("phone"), info.get("picture"),
                info.get("pseudoName"), info.get("email"), info.get("alternate"), order.get("createDate"), order.get("status")));

        query.where(cb.and(cb.equal(order.get("orderId"), orderId),
                cb.equal(order.get("user"), em.getReference(UserEntity.class, userId))));

        FullOrderDTO fullOrderDTO = em.createQuery(query).getResultList().stream().findFirst().orElse(null);
        if(fullOrderDTO != null) fullOrderDTO.setSocialNetworks(getSocialNetwork(orderId, userId));

        return fullOrderDTO;
    }

    @Override
    public List<SocialNetworkDTO> getSocialNetwork(long orderId, long userId) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SocialNetworkDTO> query = cb.createQuery(SocialNetworkDTO.class);
        Root<SocialNetwork> network = query.from(SocialNetwork.class);

        Join<SocialNetwork, OrderInformation> info = network.join("orderInformation", JoinType.LEFT);
        Join<OrderInformation, OrderEntity> order = info.join("order", JoinType.LEFT);

        query.select(cb.construct(SocialNetworkDTO.class, network.get("socialNetworkId"),
                network.get("type"), network.get("link")));
        query.where(cb.and(cb.equal(info.get("order"), em.getReference(OrderEntity.class, orderId)),
                cb.equal(order.get("user"), em.getReference(UserEntity.class, userId))));

        return em.createQuery(query).getResultList();
    }

    @Override
    public boolean updateOrder(long orderId, String fieldName, String value, long userId) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<OrderInformation> update = cb.createCriteriaUpdate(OrderInformation.class);
        Root<OrderInformation> root = update.from(OrderInformation.class);

        long orderInformationId = this.ordersSpringDAO.getOrderInformationIdByOrderAndUser(orderId,
                em.getReference(UserEntity.class, userId));
        if(orderInformationId == 0)
            return false;

        update.set(fieldName, value);
        update.where(cb.equal(root.get("orderInformationId"), orderInformationId));

        return em.createQuery(update).executeUpdate() != 0;
    }

    @Override
    public long updateOrderSocialNetworks(SocialNetworkDTO socialNetworkDTO, long userId) throws NotExistException {

        long orderInformationId = this.ordersSpringDAO.getOrderInformationIdByOrder(socialNetworkDTO.getOrderId());
        if(orderInformationId == 0)
            throw new NotExistException();

        List<ModelSocialNetworkDTO> socialNetworks = getModelSocialNetwork(socialNetworkDTO.getOrderId(), userId);

        ModelSocialNetworkDTO existSocialNetwork = null;
        for(ModelSocialNetworkDTO socialNetwork : socialNetworks){
            if(socialNetwork.getType() == socialNetworkDTO.getType()) {
                existSocialNetwork = socialNetwork;
                break;
            }
        }

        SocialNetwork socialNetwork = new SocialNetwork(socialNetworkDTO.getType(), socialNetworkDTO.getLink());
        socialNetwork.setOrderInformation(em.getReference(OrderInformation.class, orderInformationId));
        if(existSocialNetwork != null) {
            socialNetwork.setSocialNetworkId(existSocialNetwork.getId());
            em.merge(socialNetwork);
        }else
            em.persist(socialNetwork);

        return socialNetworkDTO.getOrderId();
    }

    private List<ModelSocialNetworkDTO> getModelSocialNetwork(long orderId, long userId){

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ModelSocialNetworkDTO> query = cb.createQuery(ModelSocialNetworkDTO.class);
        Root<SocialNetwork> network = query.from(SocialNetwork.class);

        Join<SocialNetwork, OrderInformation> info = network.join("orderInformation", JoinType.LEFT);
        Join<OrderInformation, OrderEntity> order = info.join("order", JoinType.LEFT);

        query.select(cb.construct(ModelSocialNetworkDTO.class, network.get("socialNetworkId"), order.get("orderId"),
                network.get("type")));
        query.where(cb.and(cb.equal(info.get("order"), em.getReference(OrderEntity.class, orderId)),
                cb.equal(order.get("user"), em.getReference(UserEntity.class, userId))));

        return em.createQuery(query).getResultList();
    }

}
