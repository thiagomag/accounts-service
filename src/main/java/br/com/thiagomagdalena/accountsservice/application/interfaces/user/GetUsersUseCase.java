package br.com.thiagomagdalena.accountsservice.application.interfaces.user;

import br.com.thiagomagdalena.accountsservice.application.interfaces.UseCase;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.user.UserResponse;

import java.util.List;

public interface GetUsersUseCase extends UseCase<String, List<UserResponse>> {
}
