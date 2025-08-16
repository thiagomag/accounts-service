package br.com.thiagomagdalena.accountsservice.application.usecases.telephone;

import br.com.thiagomagdalena.accountsservice.application.gatways.TelephoneGateway;
import br.com.thiagomagdalena.accountsservice.application.interfaces.telephone.UpdateTelephoneUseCase;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.telephone.TelephoneResponse;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.telephone.UpdateTelephoneRequest;
import br.com.thiagomagdalena.accountsservice.infrastructure.gateways.adapters.TelephoneEntityAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateTelephoneUseCaseImpl implements UpdateTelephoneUseCase {

    private final TelephoneGateway telephoneGateway;
    private final TelephoneEntityAdapter telephoneEntityAdapter;

    @Override
    public TelephoneResponse execute(UpdateTelephoneRequest updateTelephoneRequest) {
        final var telephoneId = updateTelephoneRequest.getTelephoneId();
        final var userId = updateTelephoneRequest.getUserId();
        final var telephoneRequest = updateTelephoneRequest.getTelephone();
        final var telephoneEntity = telephoneGateway.findByIdAndUserId(telephoneId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Telephone not found with id: " + telephoneId));
        final var telephoneEntityUpdated = telephoneEntityAdapter.toTelephoneEntityUpdate(telephoneRequest, telephoneEntity);
        return telephoneEntityAdapter.toTelephoneResponse(
                telephoneGateway.update(telephoneEntityUpdated)
        );
    }
}
