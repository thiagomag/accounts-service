package br.com.thiagomagdalena.accountsservice.application.interfaces.address;

import br.com.thiagomagdalena.accountsservice.application.interfaces.UseCase;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.address.AddAddressRequest;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.address.AddressResponse;

import java.util.List;

public interface AddAddressToUserUseCase extends UseCase<AddAddressRequest, List<AddressResponse>> {
}
