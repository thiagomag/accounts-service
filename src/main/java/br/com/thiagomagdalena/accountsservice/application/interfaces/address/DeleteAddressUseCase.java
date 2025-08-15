package br.com.thiagomagdalena.accountsservice.application.interfaces.address;

import br.com.thiagomagdalena.accountsservice.application.interfaces.UseCase;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.address.DeleteAddressRequest;

public interface DeleteAddressUseCase extends UseCase<DeleteAddressRequest, Void> {
}
