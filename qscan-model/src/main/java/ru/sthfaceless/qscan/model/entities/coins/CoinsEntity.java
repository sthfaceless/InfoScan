package ru.sthfaceless.qscan.model.entities.coins;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.spaceouter.infoscan.model.entities.user.UserEntity;

import javax.persistence.*;
import java.util.List;

/**
 * @author danil
 * @date 21.04.19
 */
@Entity
@Table(name = "coins")
@Data
@NoArgsConstructor
public class CoinsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coins_id", unique = true, nullable = false)
    private long coinsId;

    @Column(name = "num", nullable = false)
    private long num;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "coins")
    private List<CoinsPaymentEntity> payments;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "coins")
    private List<WalletEntity> wallets;

    @JoinColumn(name = "user_id", unique = true, nullable = false, foreignKey = @ForeignKey(name = "coins_user_fk"))
    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserEntity user;

    public CoinsEntity(long num, UserEntity user) {
        this.num = num;
        this.user = user;
    }
}
