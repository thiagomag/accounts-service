package br.com.thiagomagdalena.accountsservice.application.usecases.address;

import br.com.thiagomagdalena.accountsservice.application.gatways.UserGateway;
import br.com.thiagomagdalena.accountsservice.application.interfaces.address.AddAddressToUserUseCase;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.AddAddressRequest;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.AddressResponse;
import br.com.thiagomagdalena.accountsservice.infraestructure.gateways.adapters.AddressEntityAdapter;
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
        userEntity.getAddresses().addAll(addressesEntity);
        userGateway.save(userEntity);
        return addressEntityAdapter.toAddressResponse(addressesEntity);
    }
}
