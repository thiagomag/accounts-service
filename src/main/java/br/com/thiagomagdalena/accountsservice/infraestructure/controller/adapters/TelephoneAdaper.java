package br.com.thiagomagdalena.accountsservice.infraestructure.controller.adapters;

import br.com.thiagomagdalena.accountsservice.domain.entity.Telephone;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.TelephoneDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TelephoneAdaper {

    public List<Telephone> toTelephone(List<TelephoneDto> telephones) {
        return telephones.stream()
                .map(telephoneDto -> Telephone.builder()
                        .type(telephoneDto.type())
                        .number(telephoneDto.number())
                        .countryCode(telephoneDto.countryCode())
                        .areaCode(telephoneDto.areaCode())
                        .build())
                .toList();
    }
}
