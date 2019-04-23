package ru.spaceouter.infoscan.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.spaceouter.infoscan.model.entities.orders.OrderEntity;

/**
 * @author danil
 * @date 23.04.19
 */
@Repository
public interface OrdersSpringDAO extends CrudRepository<OrderEntity, Long> {


}
