package br.com.thiagomagdalena.accountsservice.domain.entity;

import br.com.thiagomagdalena.accountsservice.domain.enums.TelephoneTypeEnum;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Telephone {

    private String countryCode;
    private String areaCode;
    private String number;
    private TelephoneTypeEnum type;
}
