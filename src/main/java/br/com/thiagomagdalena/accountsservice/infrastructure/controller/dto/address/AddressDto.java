package br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.address;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressDto(
    String street,
    String number,
    String complement,
    String neighborhood,
    String city,
    String state,
    String country,
    @JsonProperty("postal_code")
    String postalCode
) {
}
