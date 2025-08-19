package br.com.thiagomagdalena.accountsservice.application.interfaces.user;

import br.com.thiagomagdalena.accountsservice.application.interfaces.UseCase;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user.SubscriptionDto;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user.UserResponse;

public interface ActivateSubscriptionUseCase extends UseCase<SubscriptionDto, UserResponse> {
}
