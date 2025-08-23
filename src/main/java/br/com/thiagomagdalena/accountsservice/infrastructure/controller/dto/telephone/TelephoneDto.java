package br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.telephone;

import br.com.thiagomagdalena.accountsservice.domain.enums.TelephoneTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

public record TelephoneDto(
        @JsonProperty("country_code")
        String countryCode,
        @JsonProperty("area_code")
        String areaCode,
        String number,
        TelephoneTypeEnum type
) {
}
