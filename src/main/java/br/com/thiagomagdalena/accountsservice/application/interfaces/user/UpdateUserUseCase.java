package br.com.thiagomagdalena.accountsservice.application.interfaces.user;

import br.com.thiagomagdalena.accountsservice.application.interfaces.UseCase;
import br.com.thiagomagdalena.accountsservice.domain.entity.User;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.user.UserResponse;

public interface UpdateUserUseCase extends UseCase<User, UserResponse> {
}
