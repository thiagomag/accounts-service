package br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.address;

public record AddressDto(
    String street,
    String number,
    String complement,
    String neighborhood,
    String city,
    String state,
    String country,
    String postalCode
) {
}
