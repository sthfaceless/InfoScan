package ru.sthfaceless.qscan.model.entities.orders;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

/**
 * @author danil
 * @date 21.04.19
 */
@Data
@Entity
@Table(name = "order_information")
@NoArgsConstructor
public class OrderInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_information_id", nullable = false, unique = true)
    private long orderInformationId;

    @Column(name = "first_name", length = 32)
    private String firstName;

    @Column(name = "last_name", length = 32)
    private String lastName;

    @Column(name = "patronymic", length = 32)
    private String patronymic;

    @Column(name = "ip", length = 32)
    private String ip;

    @Column(name = "phone", length = 32)
    private String phone;

    @Column(name = "picture")
    private String picture;

    @Column(name = "pseudo_name", length = 64)
    private String pseudoName;

    @Column(name = "email", length = 64)
    private String email;

    @OneToMany(mappedBy = "orderInformation", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SocialNetwork> socialNetworks;

    @Column(name = "alternate")
    @Type(type = "org.hibernate.type.TextType")
    private String alternate;

    @JoinColumn(name = "order_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "order_information_fk"))
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private OrderEntity order;

    public OrderInformation(String firstName, String lastName, String patronymic, String ip, String phone, String picture, String pseudoName, String email, String alternate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.ip = ip;
        this.phone = phone;
        this.picture = picture;
        this.pseudoName = pseudoName;
        this.email = email;
        this.alternate = alternate;
    }
}
