package br.com.thiagomagdalena.accountsservice.application.usecases.user;

import br.com.thiagomagdalena.accountsservice.application.gatways.UserGateway;
import br.com.thiagomagdalena.accountsservice.application.interfaces.user.UpdateUserUseCase;
import br.com.thiagomagdalena.accountsservice.domain.entity.User;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user.UserResponse;
import br.com.thiagomagdalena.accountsservice.infrastructure.gateways.adapters.UserEntityAdapter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserGateway userGateway;
    private final UserEntityAdapter userEntityAdapter;

    @Override
    public UserResponse execute(User user) {
        final var userId = user.getId();
        final var userEntityFromDb = userGateway.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        final var userEntityUpdated = userEntityAdapter.updateUserEntity(userEntityFromDb, user);
        return userEntityAdapter.toUserResponse(userGateway.update(userEntityUpdated));
    }
}
