package br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateUserDto(

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "O e-mail deve ser válido.")
        String email,
        @NotBlank(message = "A senha é obrigatória.")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
        String password,
        @NotBlank(message = "O nome completo é obrigatório.")
        String fullName,
        @NotBlank(message = "O cpf é obrigatório.")
        @Size(min = 11, max = 11, message = "O cpf deve ter exatamente 11 caracteres.")
        String cpf,
        @NotBlank(message = "O telefone é obrigatório.")
        List<TelephoneDto> telephones,
        @NotBlank(message = "O endereço é obrigatório.")
        List<AddressDto> addresses
) {
}
