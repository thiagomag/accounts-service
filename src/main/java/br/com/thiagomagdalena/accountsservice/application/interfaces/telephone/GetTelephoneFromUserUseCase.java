package br.com.thiagomagdalena.accountsservice.application.interfaces.telephone;

import br.com.thiagomagdalena.accountsservice.application.interfaces.UseCase;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.telephone.TelephoneResponse;

import java.util.List;

public interface GetTelephoneFromUserUseCase extends UseCase<Long, List<TelephoneResponse>> {
}
