package br.com.thiagomagdalena.accountsservice.infraestructure.gateways.adapters;

import br.com.thiagomagdalena.accountsservice.domain.entity.Telephone;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.TelephoneResponse;
import br.com.thiagomagdalena.accountsservice.infraestructure.persistence.entities.TelephoneEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TelephoneEntityAdapter {

    public List<TelephoneEntity> toTelephoneEntity(List<Telephone> telephones) {
        return telephones.stream()
                .map(telephone -> TelephoneEntity.builder()
                        .number(telephone.getNumber())
                        .type(telephone.getType())
                        .countryCode(telephone.getCountryCode())
                        .areaCode(telephone.getAreaCode())
                        .build())
                .toList();
    }

    public List<TelephoneResponse> toTelephoneResponse(List<TelephoneEntity> telephone) {
        return telephone.stream()
                .map(telephone1 -> TelephoneResponse.builder()
                        .id(telephone1.getId())
                        .number(telephone1.getNumber())
                        .type(telephone1.getType())
                        .countryCode(telephone1.getCountryCode())
                        .areaCode(telephone1.getAreaCode())
                        .build())
                .toList();
    }
}
