package ru.spaceouter.infoscan.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.spaceouter.infoscan.model.entities.orders.OrderEntity;
import ru.spaceouter.infoscan.model.entities.user.UserEntity;

/**
 * @author danil
 * @date 23.04.19
 */
@Repository
public interface OrdersSpringDAO extends CrudRepository<OrderEntity, Long> {

    @Query("select i.orderInformationId from OrderEntity o left join o.orderInformation i " +
            "where o.orderId=:order")
    long getOrderInformationIdByOrder(@Param("order") long orderId);

    @Query("select i.orderInformationId from OrderEntity o left join o.orderInformation i " +
            "where o.orderId=:order and o.user=:user")
    long getOrderInformationIdByOrderAndUser(@Param("order") long orderId, @Param("user") UserEntity userEntity);

}
