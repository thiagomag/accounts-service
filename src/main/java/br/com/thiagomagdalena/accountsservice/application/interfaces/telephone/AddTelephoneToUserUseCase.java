package br.com.thiagomagdalena.accountsservice.application.interfaces.telephone;

import br.com.thiagomagdalena.accountsservice.application.interfaces.UseCase;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.telephone.AddTelephoneRequest;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.telephone.TelephoneResponse;

import java.util.List;

public interface AddTelephoneToUserUseCase extends UseCase<AddTelephoneRequest, List<TelephoneResponse>> {
}
