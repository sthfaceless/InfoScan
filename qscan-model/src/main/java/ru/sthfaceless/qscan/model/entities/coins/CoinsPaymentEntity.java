package ru.sthfaceless.qscan.model.entities.coins;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.spaceouter.infoscan.dto.coins.BillingMethod;
import ru.spaceouter.infoscan.dto.coins.PaymentStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * @author danil
 * @date 21.04.19
 */
@Entity
@Data
@Table(name = "coins_payment")
@NoArgsConstructor
public class CoinsPaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coins_payment_id", unique = true, nullable = false)
    private long coinsPaymentId;

    @Column(name = "quantity", nullable = false)
    private long quantity;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "method", nullable = false)
    @Enumerated(EnumType.STRING)
    private BillingMethod method;

    @Column(name = "payment_uuid", nullable = false, unique = true)
    private String paymentUUID;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus status;

    @JoinColumn(name = "coins_id", nullable = false, foreignKey = @ForeignKey(name = "coins_payments_fk"))
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CoinsEntity coins;

    public CoinsPaymentEntity(long quantity, Date date, BillingMethod method, String uuid, PaymentStatus status) {
        this.quantity = quantity;
        this.date = date;
        this.method = method;
        this.paymentUUID = uuid;
        this.status = status;
    }
}
