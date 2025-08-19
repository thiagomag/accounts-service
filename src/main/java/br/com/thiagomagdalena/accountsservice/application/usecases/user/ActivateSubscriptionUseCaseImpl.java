package br.com.thiagomagdalena.accountsservice.application.usecases.user;

import br.com.thiagomagdalena.accountsservice.application.gatways.UserGateway;
import br.com.thiagomagdalena.accountsservice.application.interfaces.user.ActivateSubscriptionUseCase;
import br.com.thiagomagdalena.accountsservice.domain.enums.SubscriptionStatusEnum;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user.SubscriptionDto;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user.UserResponse;
import br.com.thiagomagdalena.accountsservice.infrastructure.gateways.adapters.UserEntityAdapter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ActivateSubscriptionUseCaseImpl implements ActivateSubscriptionUseCase {

    private final UserGateway userGateway;
    private final UserEntityAdapter userEntityAdapter;

    @Override
    public UserResponse execute(SubscriptionDto subscriptionDto) {
        final var userEntity = userGateway.findById(subscriptionDto.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (userEntity.getSubscriptionStatus() == SubscriptionStatusEnum.ACTIVE) {
            throw new IllegalArgumentException("User already has an active subscription");
        }
        userEntity.setSubscriptionStatus(SubscriptionStatusEnum.ACTIVE);
        userEntity.setSubscriptionEndDate(LocalDate.now().plusDays(subscriptionDto.durationInDays()));
        userGateway.update(userEntity);
        return userEntityAdapter.toUserResponse(userEntity);
    }
}
