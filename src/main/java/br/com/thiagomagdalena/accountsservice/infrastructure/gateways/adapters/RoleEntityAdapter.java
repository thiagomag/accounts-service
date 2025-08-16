package br.com.thiagomagdalena.accountsservice.infrastructure.gateways.adapters;

import br.com.thiagomagdalena.accountsservice.domain.entity.Role;
import br.com.thiagomagdalena.accountsservice.infrastructure.persistence.entities.RoleEntity;
import br.com.thiagomagdalena.accountsservice.infrastructure.persistence.entities.UserRoleEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class RoleEntityAdapter {

    public RoleEntity toRoleEntity(final Role role) {
        if (role == null) {
            return null;
        }
        return RoleEntity.builder()
                .name(role.getName())
                .build();
    }

    public List<Role> toRole(final Set<UserRoleEntity> userRoleEntityList) {
        if (userRoleEntityList == null || userRoleEntityList.isEmpty()) {
            return new ArrayList<>();
        }
        return userRoleEntityList.stream()
                .map(userRoleEntity -> Role.builder()
                        .name(userRoleEntity.getRole().getName())
                        .build())
                .toList();
    }
}
