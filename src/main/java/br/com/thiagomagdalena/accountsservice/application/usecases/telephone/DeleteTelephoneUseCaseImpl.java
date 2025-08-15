package br.com.thiagomagdalena.accountsservice.application.usecases.telephone;

import br.com.thiagomagdalena.accountsservice.application.gatways.TelephoneGateway;
import br.com.thiagomagdalena.accountsservice.application.interfaces.telephone.DeleteTelephoneUseCase;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.telephone.DeleteTelephoneRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteTelephoneUseCaseImpl implements DeleteTelephoneUseCase {

    private final TelephoneGateway telephoneGateway;

    @Override
    public Void execute(DeleteTelephoneRequest deleteTelephoneRequest) {
        final var telephoneId = deleteTelephoneRequest.getTelephoneId();
        final var userId = deleteTelephoneRequest.getUserId();
        final var telephoneEntity = telephoneGateway.findByIdAndUserId(telephoneId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Telephone not found with id: " + telephoneId + " for user id: " + userId));
        telephoneGateway.delete(telephoneEntity);
        return null;
    }
}
