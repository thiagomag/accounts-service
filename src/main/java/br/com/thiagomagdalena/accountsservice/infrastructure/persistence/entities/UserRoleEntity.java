package br.com.thiagomagdalena.accountsservice.infrastructure.persistence.entities;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

@Table(name = "user_roles")
@Entity(name = "UserRole")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserRoleEntity extends BaseEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}
