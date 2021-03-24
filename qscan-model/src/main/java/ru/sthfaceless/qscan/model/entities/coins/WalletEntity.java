package ru.sthfaceless.qscan.model.entities.coins;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.spaceouter.infoscan.dto.coins.BillingMethod;

import javax.persistence.*;

@Data
@Entity
@Table(name = "wallets")
@NoArgsConstructor
public class WalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id", unique = true, nullable = false)
    private long walletId;

    @Column(name = "system_id", nullable = false)
    private String systemId;

    @Column(name = "method", nullable = false)
    @Enumerated(EnumType.STRING)
    private BillingMethod method;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "coins_id", nullable = false, foreignKey = @ForeignKey(name = "wallet_coins_fk"))
    private CoinsEntity coins;

    public WalletEntity(String systemId, BillingMethod method, CoinsEntity coins) {
        this.systemId = systemId;
        this.method = method;
        this.coins = coins;
    }
}
