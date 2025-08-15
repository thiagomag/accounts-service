package br.com.thiagomagdalena.accountsservice.application.usecases.address;

import br.com.thiagomagdalena.accountsservice.application.gatways.AddressGateway;
import br.com.thiagomagdalena.accountsservice.application.interfaces.address.GetAddressFromUserUseCase;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.address.AddressResponse;
import br.com.thiagomagdalena.accountsservice.infraestructure.gateways.adapters.AddressEntityAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAddressFromUserUseCaseImpl implements GetAddressFromUserUseCase {

    private final AddressGateway addressGateway;
    private final AddressEntityAdapter addressEntityAdapter;

    @Override
    public List<AddressResponse> execute(Long userId) {
        final var userAddresses = addressGateway.findByUserId(userId);
        if (userAddresses.isEmpty()) {
            return new ArrayList<>();
        }
        return addressEntityAdapter.toAddressResponse(userAddresses);
    }
}
