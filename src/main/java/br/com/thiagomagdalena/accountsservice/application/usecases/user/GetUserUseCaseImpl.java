package br.com.thiagomagdalena.accountsservice.application.usecases.user;

import br.com.thiagomagdalena.accountsservice.application.gatways.UserGateway;
import br.com.thiagomagdalena.accountsservice.application.interfaces.user.GetUserUseCase;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.user.UserResponse;
import br.com.thiagomagdalena.accountsservice.infraestructure.gateways.adapters.UserEntityAdapter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {

    private final UserGateway userGateway;
    private final UserEntityAdapter userEntityAdapter;

    @Override
    public UserResponse execute(Long userId) {
        final var user = userGateway.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        return userEntityAdapter.toUserResponse(user);
    }
}
