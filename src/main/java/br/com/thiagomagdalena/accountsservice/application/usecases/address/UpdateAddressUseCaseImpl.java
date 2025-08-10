package br.com.thiagomagdalena.accountsservice.application.usecases.address;

import br.com.thiagomagdalena.accountsservice.application.interfaces.address.UpdateAddressUseCase;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.AddressResponse;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.UpdateAddressRequest;
import br.com.thiagomagdalena.accountsservice.infraestructure.gateways.AddressRepositoryGateway;
import br.com.thiagomagdalena.accountsservice.infraestructure.gateways.adapters.AddressEntityAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateAddressUseCaseImpl implements UpdateAddressUseCase {

    private final AddressRepositoryGateway addressRepositoryGateway;
    private final AddressEntityAdapter addressEntityAdapter;

    @Override
    public AddressResponse execute(UpdateAddressRequest updateAddressRequest) {
        final var addressId = updateAddressRequest.getAddressId();
        final var userId = updateAddressRequest.getUserId();
        final var addressUpdate = updateAddressRequest.getAddress();
        final var addressInDb = addressRepositoryGateway.findByIdAndUserId(addressId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Address not found for user with id: " + userId + " and address id: " + addressId));
        final var addressEntity = addressEntityAdapter.toAddressEntityUpdate(addressUpdate, addressInDb);
        final var addressUpdated = addressRepositoryGateway.update(addressEntity);
        return addressEntityAdapter.toAddressResponse(addressUpdated);
    }
}
