package ru.spaceouter.infoscan.model.entities.orders;

import lombok.Data;
import lombok.NoArgsConstructor;
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
public class SocialNetwork {

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
