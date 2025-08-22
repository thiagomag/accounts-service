package br.com.thiagomagdalena.accountsservice.application.interfaces.user;

import br.com.thiagomagdalena.accountsservice.application.interfaces.UseCase;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user.UserResponse;

public interface GetLoggedUserUseCase extends UseCase<Long, UserResponse> {
}
