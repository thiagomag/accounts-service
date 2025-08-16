package br.com.thiagomagdalena.accountsservice.application.interfaces.telephone;

import br.com.thiagomagdalena.accountsservice.application.interfaces.UseCase;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.telephone.DeleteTelephoneRequest;

public interface DeleteTelephoneUseCase extends UseCase<DeleteTelephoneRequest, Void> {
}
