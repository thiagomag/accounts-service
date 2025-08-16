package br.com.thiagomagdalena.accountsservice.application.usecases.address;

import br.com.thiagomagdalena.accountsservice.application.gatways.UserGateway;
import br.com.thiagomagdalena.accountsservice.application.interfaces.address.AddAddressToUserUseCase;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.address.AddAddressRequest;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.address.AddressResponse;
import br.com.thiagomagdalena.accountsservice.infrastructure.gateways.adapters.AddressEntityAdapter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddAddressToUserUseCaseImpl implements AddAddressToUserUseCase {

    private final UserGateway userGateway;
    private final AddressEntityAdapter addressEntityAdapter;

    @Override
    public List<AddressResponse> execute(AddAddressRequest request) {
        final var userId = request.getUserId();
        final var addresses = request.getAddresses();
        final var userEntity = userGateway.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        final var addressesEntity = addressEntityAdapter.toAddressEntity(addresses);
        addressesEntity.forEach(addressEntity -> addressEntity.setUser(userEntity));
        userEntity.getAddresses().addAll(addressesEntity);
        final var userEntityUpdated = userGateway.update(userEntity);
        return addressEntityAdapter.toAddressResponse(userEntityUpdated.getAddresses());
    }
}
