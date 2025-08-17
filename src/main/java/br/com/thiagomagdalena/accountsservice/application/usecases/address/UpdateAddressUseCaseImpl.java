package br.com.thiagomagdalena.accountsservice.application.usecases.address;

import br.com.thiagomagdalena.accountsservice.application.gatways.AddressGateway;
import br.com.thiagomagdalena.accountsservice.application.interfaces.address.UpdateAddressUseCase;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.address.AddressResponse;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.address.UpdateAddressRequest;
import br.com.thiagomagdalena.accountsservice.infrastructure.gateways.adapters.AddressEntityAdapter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateAddressUseCaseImpl implements UpdateAddressUseCase {

    private final AddressGateway addressGateway;
    private final AddressEntityAdapter addressEntityAdapter;

    @Override
    public AddressResponse execute(UpdateAddressRequest updateAddressRequest) {
        final var addressId = updateAddressRequest.getAddressId();
        final var userId = updateAddressRequest.getUserId();
        final var addressUpdate = updateAddressRequest.getAddress();
        final var addressInDb = addressGateway.findByIdAndUserId(addressId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Address not found for user with id: " + userId + " and address id: " + addressId));
        final var addressEntity = addressEntityAdapter.toAddressEntityUpdate(addressUpdate, addressInDb);
        final var addressUpdated = addressGateway.update(addressEntity);
        return addressEntityAdapter.toAddressResponse(addressUpdated);
    }
}
