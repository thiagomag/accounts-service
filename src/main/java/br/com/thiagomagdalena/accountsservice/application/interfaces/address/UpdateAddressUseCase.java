package br.com.thiagomagdalena.accountsservice.application.interfaces.address;

import br.com.thiagomagdalena.accountsservice.application.interfaces.UseCase;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.address.AddressResponse;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.address.UpdateAddressRequest;

public interface UpdateAddressUseCase extends UseCase<UpdateAddressRequest, AddressResponse> {
}
