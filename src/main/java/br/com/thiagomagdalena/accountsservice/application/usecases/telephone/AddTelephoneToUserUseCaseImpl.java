package br.com.thiagomagdalena.accountsservice.application.usecases.telephone;

import br.com.thiagomagdalena.accountsservice.application.gatways.UserGateway;
import br.com.thiagomagdalena.accountsservice.application.interfaces.telephone.AddTelephoneToUserUseCase;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.telephone.AddTelephoneRequest;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.telephone.TelephoneResponse;
import br.com.thiagomagdalena.accountsservice.infrastructure.gateways.adapters.TelephoneEntityAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddTelephoneToUserUseCaseImpl implements AddTelephoneToUserUseCase {

    private final UserGateway userGateway;
    private final TelephoneEntityAdapter telephoneEntityAdapter;

    @Override
    public List<TelephoneResponse> execute(AddTelephoneRequest request) {
        final var userId = request.getUserId();
        final var telephonesRequest = request.getTelephones();
        final var userEntity = userGateway.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        final var telephonesEntities = telephoneEntityAdapter.toTelephoneEntity(telephonesRequest);
        telephonesEntities.forEach(telephone -> telephone.setUser(userEntity));
        userEntity.getTelephones().addAll(telephonesEntities);
        final var userEntityUpdated = userGateway.update(userEntity);
        return telephoneEntityAdapter.toTelephoneResponse(userEntityUpdated.getTelephones());
    }
}
