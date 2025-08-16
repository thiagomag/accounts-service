package br.com.thiagomagdalena.accountsservice.application.interfaces.user;

import br.com.thiagomagdalena.accountsservice.application.interfaces.UseCase;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user.LoginUserDto;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user.RecoveryJwtTokenDto;

public interface AuthenticateUserUseCase extends UseCase<LoginUserDto, RecoveryJwtTokenDto> {
}
