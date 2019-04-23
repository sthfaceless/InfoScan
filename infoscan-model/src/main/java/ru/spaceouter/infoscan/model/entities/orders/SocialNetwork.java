package ru.spaceouter.infoscan.model.entities.orders;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import ru.spaceouter.infoscan.dto.orders.SocialNetworkType;

import javax.persistence.*;

/**
 * @author danil
 * @date 21.04.19
 */
@Entity
@Data
@Table(name = "order_social_networks")
@NoArgsConstructor
@DynamicUpdate
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "getSocialNetworksByOrder",
            query = "select new ru.spaceouter.infoscan.dto.orders.SocialNetworkDTO(sn.socialNetworkId, sn.type, sn.link) from SocialNetwork sn " +
                    "left join sn.orderInformation i where i.order = :order"),
        @org.hibernate.annotations.NamedQuery(name = "getSocialNetworksByID",
        query = "select new ru.spaceouter.infoscan.dto.model.SocialNetworkWithOrderDTO(sn.socialNetworkId, o.orderId) from SocialNetwork sn " +
                " left join sn.orderInformation i " +
                " left join i.order o " +
                " where sn.socialNetworkId = :id and o.user = :user")
})
public class SocialNetwork {

    public static final String GET_SOCIAL_NETWORKS_BY_ORDER = "getSocialNetworksByOrder";
    public static final String GET_SOCIAL_NETWORKS_BY_ID = "getSocialNetworksByID";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "social_network_id", nullable = false, unique = true)
    private long socialNetworkId;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialNetworkType type;

    @Column(name = "link", nullable = false)
    private String link;

    @JoinColumn(name = "order_information_id", nullable = false, foreignKey = @ForeignKey(name = "social_network_order_information_fk"))
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private OrderInformation orderInformation;

    public SocialNetwork(SocialNetworkType type, String link) {
        this.type = type;
        this.link = link;
    }
}
