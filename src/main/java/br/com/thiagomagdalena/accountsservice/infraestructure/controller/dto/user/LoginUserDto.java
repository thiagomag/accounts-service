package br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.user;

public record LoginUserDto(

        String email,
        String password

) {
}