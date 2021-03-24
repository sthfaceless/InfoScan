package ru.spaceouter.infoscan.model.entities.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.spaceouter.infoscan.model.entities.coins.CoinsEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author danil
 * @date 21.04.19
 */
@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private long userId;

    @Column(name = "username", unique = true, nullable = false, length = 64)
    private String username;

    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL,optional = false, fetch = FetchType.LAZY, orphanRemoval = true)
    private AuthEntity auth;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = false, orphanRemoval = true, fetch = FetchType.LAZY)
    private CoinsEntity coins;

    public UserEntity(String username, Date registrationDate) {
        this.username = username;
        this.registrationDate = registrationDate;
    }
}
