package br.com.thiagomagdalena.accountsservice.application.usecases.user;

import br.com.thiagomagdalena.accountsservice.application.gatways.RoleGateway;
import br.com.thiagomagdalena.accountsservice.application.gatways.UserGateway;
import br.com.thiagomagdalena.accountsservice.application.interfaces.user.CreateUserUseCase;
import br.com.thiagomagdalena.accountsservice.domain.entity.User;
import br.com.thiagomagdalena.accountsservice.domain.enums.RoleNameEnum;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.user.UserResponse;
import br.com.thiagomagdalena.accountsservice.infraestructure.gateways.adapters.UserEntityAdapter;
import br.com.thiagomagdalena.accountsservice.infraestructure.persistence.entities.RoleEntity;
import br.com.thiagomagdalena.accountsservice.infraestructure.persistence.entities.UserEntity;
import br.com.thiagomagdalena.accountsservice.infraestructure.persistence.entities.UserRoleEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserGateway userGateway;
    private final RoleGateway roleGateway;
    private final UserEntityAdapter userEntityAdapter;

    @Override
    public UserResponse execute(User createUser) {
        final var roleEntity = roleGateway.findByRoleName(RoleNameEnum.ROLE_BASIC.name())
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        final var userEntity = userEntityAdapter.toUserEntity(createUser);
        setRoleUserEntity(userEntity, roleEntity);
        return userEntityAdapter.toUserResponse(userGateway.save(userEntity));
    }

    private void setRoleUserEntity(UserEntity userEntity, RoleEntity roleEntity) {
        userEntity.setUserRoles(Set.of(UserRoleEntity.builder()
                        .user(userEntity)
                        .role(roleEntity)
                .build()));
    }
}
