package br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto;

public record LoginUserDto(

        String email,
        String password

) {
}