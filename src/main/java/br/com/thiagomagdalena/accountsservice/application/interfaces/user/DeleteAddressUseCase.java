package br.com.thiagomagdalena.accountsservice.application.interfaces.user;

import br.com.thiagomagdalena.accountsservice.application.interfaces.UseCase;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.DeleteAddressRequest;

public interface DeleteAddressUseCase extends UseCase<DeleteAddressRequest, Void> {
}
