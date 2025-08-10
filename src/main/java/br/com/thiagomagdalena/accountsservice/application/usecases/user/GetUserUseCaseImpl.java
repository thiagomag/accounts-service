package br.com.thiagomagdalena.accountsservice.application.usecases.user;

import br.com.thiagomagdalena.accountsservice.application.interfaces.user.GetUserUseCase;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.UserResponse;
import br.com.thiagomagdalena.accountsservice.infraestructure.gateways.UserRepositoryGateway;
import br.com.thiagomagdalena.accountsservice.infraestructure.gateways.adapters.UserEntityAdapter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {

    private final UserRepositoryGateway userRepositoryGateway;
    private final UserEntityAdapter userEntityAdapter;

    @Override
    public UserResponse execute(Long userId) {
        final var user = userRepositoryGateway.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        return userEntityAdapter.toUserResponse(user);
    }
}
