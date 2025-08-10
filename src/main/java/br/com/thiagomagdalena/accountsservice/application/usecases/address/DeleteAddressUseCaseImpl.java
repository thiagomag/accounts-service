package br.com.thiagomagdalena.accountsservice.application.usecases.address;

import br.com.thiagomagdalena.accountsservice.application.gatways.AddressGateway;
import br.com.thiagomagdalena.accountsservice.application.interfaces.user.DeleteAddressUseCase;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.DeleteAddressRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteAddressUseCaseImpl implements DeleteAddressUseCase {

    private final AddressGateway addressGateway;

    @Override
    public Void execute(DeleteAddressRequest deleteAddressRequest) {
        final var addressToDelete = addressGateway.findByIdAndUserId(deleteAddressRequest.getAddressId(), deleteAddressRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + deleteAddressRequest.getAddressId() + " for user id: " + deleteAddressRequest.getUserId()));
        addressGateway.delete(addressToDelete);
        return null;
    }
}
