package br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record SubscriptionDto(

        @NotNull(message = "O ID do usuário não pode ser nulo.")
        Long userId,
        @NotNull(message = "A duração da assinatura não pode ser nula.")
        @PositiveOrZero(message = "A duração da assinatura não pode ser um valor negativo.") // <-- ADICIONE AQUI
        Integer durationInDays
) {
}
