package ru.spaceouter.infoscan.model.entities.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.spaceouter.infoscan.dto.auth.Role;

import javax.persistence.*;

/**
 * @author danil
 * @date 21.04.19
 */
@Entity
@Data
@Table(name = "users_roles")
@NoArgsConstructor
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false, unique = true)
    private long roleId;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @JoinColumn(name = "auth_id", nullable = false, foreignKey = @ForeignKey(name = "auth_role_fk"))
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private AuthEntity auth;

    public RoleEntity(Role role) {
        this.role = role;
    }
}
