package br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.telephone;

import br.com.thiagomagdalena.accountsservice.domain.enums.TelephoneTypeEnum;

public record TelephoneDto(
    String countryCode,
    String areaCode,
    String number,
    TelephoneTypeEnum type
) {
}
