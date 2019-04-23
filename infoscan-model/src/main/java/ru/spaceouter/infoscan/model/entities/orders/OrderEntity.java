package ru.spaceouter.infoscan.model.entities.orders;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.spaceouter.infoscan.dto.orders.OrderStatus;
import ru.spaceouter.infoscan.model.entities.user.UserEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author danil
 * @date 21.04.19
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "getOrderInformationByOrderAndUser",
        query = "select i.orderInformationId from OrderEntity o left join o.orderInformation i " +
                "where o.orderId = :order and o.user = :user")
})
public class OrderEntity {

    public static final String GET_ORDER_INFORMATION_BY_ORDER_AND_USER = "getOrderInformationByOrderAndUser";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false, unique = true)
    private long orderId;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "order_name", nullable = false)
    private String orderName;

    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "user_order_fk"))
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    private UserEntity user;

    @OneToOne(mappedBy = "order", optional = false, orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private OrderInformation orderInformation;

    public OrderEntity(Date createDate, OrderStatus status, String orderName) {
        this.createDate = createDate;
        this.status = status;
        this.orderName = orderName;
    }
}
