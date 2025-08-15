package br.com.thiagomagdalena.accountsservice.application.interfaces.address;

import br.com.thiagomagdalena.accountsservice.application.interfaces.UseCase;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.address.AddressResponse;

import java.util.List;

public interface GetAddressFromUserUseCase extends UseCase<Long, List<AddressResponse>> {
}
