package br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginUserDto(

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "O e-mail deve ser válido.")
        String email,
        @NotBlank(message = "A senha é obrigatória.")
        String password

) {
}