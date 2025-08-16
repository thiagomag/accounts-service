package br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.telephone;

import br.com.thiagomagdalena.accountsservice.domain.enums.TelephoneTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TelephoneResponse {

    private Long id;
    private String countryCode;
    private String areaCode;
    private String number;
    private TelephoneTypeEnum type;
}
