package br.com.thiagomagdalena.accountsservice.application.interfaces.address;

import br.com.thiagomagdalena.accountsservice.application.interfaces.UseCase;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.AddressResponse;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.UpdateAddressRequest;

public interface UpdateAddressUseCase extends UseCase<UpdateAddressRequest, AddressResponse> {
}
