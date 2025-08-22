package br.com.thiagomagdalena.accountsservice.application.usecases.user;

import br.com.thiagomagdalena.accountsservice.application.gatways.UserGateway;
import br.com.thiagomagdalena.accountsservice.application.interfaces.user.GetLoggedUserUseCase;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user.UserResponse;
import br.com.thiagomagdalena.accountsservice.infrastructure.gateways.adapters.UserEntityAdapter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetLoggedUserUseCaseImpl implements GetLoggedUserUseCase {

    private final UserGateway userGateway;
    private final UserEntityAdapter userEntityAdapter;

    @Override
    public UserResponse execute(Long userId) {
        final var user = userGateway.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        return userEntityAdapter.toUserResponse(user);
    }
}
