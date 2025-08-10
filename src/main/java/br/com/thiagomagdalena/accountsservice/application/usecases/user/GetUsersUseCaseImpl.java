package br.com.thiagomagdalena.accountsservice.application.usecases.user;

import br.com.thiagomagdalena.accountsservice.application.gatways.UserGateway;
import br.com.thiagomagdalena.accountsservice.application.interfaces.user.GetUsersUseCase;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.UserResponse;
import br.com.thiagomagdalena.accountsservice.infraestructure.gateways.adapters.UserEntityAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUsersUseCaseImpl implements GetUsersUseCase {

    private final UserGateway userRepository;
    private final UserEntityAdapter userEntityAdapter;

    @Override
    public List<UserResponse> execute(String employeeType) {
        return userRepository.findAll()
                .stream()
                .map(userEntityAdapter::toUserResponse)
                .toList();
    }
}
