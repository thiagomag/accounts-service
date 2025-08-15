package br.com.thiagomagdalena.accountsservice.application.interfaces.telephone;

import br.com.thiagomagdalena.accountsservice.application.interfaces.UseCase;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.telephone.TelephoneResponse;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.telephone.UpdateTelephoneRequest;

public interface UpdateTelephoneUseCase extends UseCase<UpdateTelephoneRequest, TelephoneResponse> {
}
