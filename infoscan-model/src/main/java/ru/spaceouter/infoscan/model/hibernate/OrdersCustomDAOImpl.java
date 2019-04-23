package ru.spaceouter.infoscan.model.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.spaceouter.infoscan.dto.model.SocialNetworkWithOrderDTO;
import ru.spaceouter.infoscan.dto.orders.FullOrderDTO;
import ru.spaceouter.infoscan.dto.orders.OrderStatus;
import ru.spaceouter.infoscan.dto.orders.SocialNetworkDTO;
import ru.spaceouter.infoscan.dto.view.ViewOrderDTO;
import ru.spaceouter.infoscan.model.OrdersCustomDAO;
import ru.spaceouter.infoscan.model.entities.orders.OrderEntity;
import ru.spaceouter.infoscan.model.entities.orders.OrderInformation;
import ru.spaceouter.infoscan.model.entities.orders.SocialNetwork;
import ru.spaceouter.infoscan.model.entities.user.UserEntity;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author danil
 * @date 23.04.19
 */
public class OrdersCustomDAOImpl extends AbstractHibernateDAO implements OrdersCustomDAO{

    public OrdersCustomDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<ViewOrderDTO> getOrdersByUser(long userId, int start, int max) {

        Session session = session();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ViewOrderDTO> query = cb.createQuery(ViewOrderDTO.class);
        Root<OrderEntity> order = query.from(OrderEntity.class);

        Join<OrderEntity, OrderInformation> info = order.join("orderInformation", JoinType.LEFT);

        query.select(cb.construct(ViewOrderDTO.class, order.get("orderId"),
                info.get("firstName"), info.get("lastName"), info.get("patronymic"), info.get("pseudoName"),
                order.get("createDate"), order.get("status")));

        query.orderBy(cb.desc(order.get("createDate")));

        return session.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    @Override
    public FullOrderDTO getFullOrder(long orderId, long userId) {
        Session session = session();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<FullOrderDTO> query = cb.createQuery(FullOrderDTO.class);
        Root<OrderEntity> order = query.from(OrderEntity.class);

        Join<OrderEntity, OrderInformation> info = order.join("orderInformation", JoinType.LEFT);

        query.select(cb.construct(FullOrderDTO.class, order.get("orderId"),
                info.get("firstName"), info.get("lastName"), info.get("patronymic"),
                info.get("ip"), info.get("phone"), info.get("picture"),
                info.get("pseudoName"), info.get("email"), info.get("alternate")));

        query.where(cb.and(cb.equal(order.get("orderId"), orderId),
                cb.equal(order.get("user"), session.load(UserEntity.class, userId))));

        FullOrderDTO fullOrderDTO = session.createQuery(query).getResultList().stream().findFirst().orElse(null);
        if(fullOrderDTO != null) fullOrderDTO.setSocialNetworks(getSocialNetwork(orderId, userId));

        return fullOrderDTO;
    }

    @Override
    public List<SocialNetworkDTO> getSocialNetwork(long orderId, long userId) {

        Session session = session();
        return session.createNamedQuery(SocialNetwork.GET_SOCIAL_NETWORKS_BY_ORDER, SocialNetworkDTO.class)
                .setParameter("order", session.load(Order.class, orderId))
                .getResultList();
    }

    @Override
    public long saveOrder(long userId, FullOrderDTO fullOrderDTO) {

        Session session = session();

        OrderEntity orderEntity = new OrderEntity(new Date(),
                OrderStatus.ACCEPTED,
                "Anonymous");
        orderEntity.setUser(session.load(UserEntity.class, userId));

        OrderInformation orderInformation = new OrderInformation(
                fullOrderDTO.getFirstName(),
                fullOrderDTO.getLastName(),
                fullOrderDTO.getPatronymic(),
                fullOrderDTO.getIp(),
                fullOrderDTO.getPhone(),
                fullOrderDTO.getPicture(),
                fullOrderDTO.getPseudoName(),
                fullOrderDTO.getEmail(),
                fullOrderDTO.getAlternate()
        );
        orderInformation.setOrder(orderEntity);
        orderEntity.setOrderInformation(orderInformation);


        return (long) session.save(orderEntity);
    }

    @Override
    public boolean updateOrder(long orderId, String fieldName, String value, long userId) {
        Session session = session();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaUpdate<OrderInformation> update = cb.createCriteriaUpdate(OrderInformation.class);
        Root<OrderInformation> root = update.from(OrderInformation.class);

        long orderInformationId = getOrderInformationIdByOrderAndUser(orderId, userId);
        if(orderInformationId == 0)
            return false;

        update.set(fieldName, value);
        update.where(cb.equal(root.get("orderInformationId"), orderInformationId));

        return session.createQuery(update).executeUpdate() != 0;
    }

    private long getOrderInformationIdByOrderAndUser(long orderId, long userId){

        Session session = session();
        return session.createNamedQuery(OrderEntity.GET_ORDER_INFORMATION_BY_ORDER_AND_USER, Long.class)
                .setParameter("order", orderId)
                .setParameter("user", session.load(UserEntity.class, userId))
                .getResultList().stream().findFirst().orElse((long) 0);
    }

    @Override
    public long updateOrderSocialNetworks(SocialNetworkDTO socialNetworkDTO, long userId) {

        Session session = session();

        SocialNetworkWithOrderDTO socialNetworkByUser = getSocialNetworkByUser(socialNetworkDTO.getId(), userId);
        if(socialNetworkByUser == null)
            return 0;

        SocialNetwork socialNetwork = new SocialNetwork(socialNetworkDTO.getType(), socialNetworkDTO.getLink());
        socialNetwork.setSocialNetworkId(socialNetworkDTO.getId());

        session.update(socialNetwork);
        return socialNetworkByUser.getOrderId();
    }

    private SocialNetworkWithOrderDTO getSocialNetworkByUser(long snId, long userId){
        Session session = session();
        return session.createNamedQuery(SocialNetwork.GET_SOCIAL_NETWORKS_BY_ID, SocialNetworkWithOrderDTO.class)
                .setParameter("id", snId)
                .setParameter("user", session.load(UserEntity.class, userId))
                .getResultList().stream().findFirst().orElse(null);
    }
}
