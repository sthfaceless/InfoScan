package ru.spaceouter.infoscan.model.entities.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author danil
 * @date 22.04.19
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "activations")
public class ActivationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activations_id", unique = true, nullable = false)
    private long activationId;

    @Column(name = "activate_account")
    private String activateAccount;

    @Column(name = "confirm_restore")
    private String confirmRestore;

    @Column(name = "confirm_email")
    private String confirmEmail;

    @Column(name = "temp_email")
    private String tempEmail;

    @JoinColumn(name = "auth_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "auth_activations_fk"))
    @OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AuthEntity auth;

    public ActivationEntity(AuthEntity auth) {
        this.auth = auth;
    }
}
