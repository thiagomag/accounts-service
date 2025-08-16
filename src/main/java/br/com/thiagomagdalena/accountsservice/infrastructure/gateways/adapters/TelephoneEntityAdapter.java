package br.com.thiagomagdalena.accountsservice.infrastructure.gateways.adapters;

import br.com.thiagomagdalena.accountsservice.domain.entity.Telephone;
import br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.telephone.TelephoneResponse;
import br.com.thiagomagdalena.accountsservice.infrastructure.persistence.entities.TelephoneEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TelephoneEntityAdapter {

    public Set<TelephoneEntity> toTelephoneEntity(List<Telephone> telephones) {
        return telephones.stream()
                .map(telephone -> TelephoneEntity.builder()
                        .number(telephone.getNumber())
                        .type(telephone.getType())
                        .countryCode(telephone.getCountryCode())
                        .areaCode(telephone.getAreaCode())
                        .build())
                .collect(Collectors.toSet());
    }

    public List<TelephoneResponse> toTelephoneResponse(Set<TelephoneEntity> telephone) {
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

    public TelephoneEntity toTelephoneEntityUpdate(Telephone telephone, TelephoneEntity telephoneEntity) {
        Optional.ofNullable(telephone.getAreaCode()).ifPresent(telephoneEntity::setAreaCode);
        Optional.ofNullable(telephone.getNumber()).ifPresent(telephoneEntity::setNumber);
        Optional.ofNullable(telephone.getType()).ifPresent(telephoneEntity::setType);
        Optional.ofNullable(telephone.getCountryCode()).ifPresent(telephoneEntity::setCountryCode);
        return telephoneEntity;
    }

    public TelephoneResponse toTelephoneResponse(TelephoneEntity telephoneEntity) {
        return TelephoneResponse.builder()
                .id(telephoneEntity.getId())
                .number(telephoneEntity.getNumber())
                .type(telephoneEntity.getType())
                .countryCode(telephoneEntity.getCountryCode())
                .areaCode(telephoneEntity.getAreaCode())
                .build();
    }
}
