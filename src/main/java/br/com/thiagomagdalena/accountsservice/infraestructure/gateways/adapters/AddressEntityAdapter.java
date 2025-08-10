package br.com.thiagomagdalena.accountsservice.infraestructure.gateways.adapters;

import br.com.thiagomagdalena.accountsservice.domain.entity.Address;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.AddressResponse;
import br.com.thiagomagdalena.accountsservice.infraestructure.persistence.entities.AddressEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AddressEntityAdapter {

    public List<AddressEntity> toAddressEntity(List<Address> addresses) {
        return addresses.stream()
                .map(address -> AddressEntity.builder()
                        .street(address.getStreet())
                        .number(address.getNumber())
                        .city(address.getCity())
                        .state(address.getState())
                        .postalCode(address.getPostalCode())
                        .country(address.getCountry())
                        .complement(address.getComplement())
                        .neighborhood(address.getNeighborhood())
                        .build())
                .toList();
    }

    public AddressEntity toAddressEntityUpdate(Address address, AddressEntity addressEntity) {
        return AddressEntity.builder()
                .id(addressEntity.getId())
                .street(Optional.ofNullable(address.getStreet()).orElse(addressEntity.getStreet()))
                .city(Optional.ofNullable(address.getCity()).orElse(addressEntity.getCity()))
                .state(Optional.ofNullable(address.getState()).orElse(addressEntity.getState()))
                .postalCode(Optional.ofNullable(address.getPostalCode()).orElse(addressEntity.getPostalCode()))
                .country(Optional.ofNullable(address.getCountry()).orElse(addressEntity.getCountry()))
                .complement(Optional.ofNullable(address.getComplement()).orElse(addressEntity.getComplement()))
                .neighborhood(Optional.ofNullable(address.getNeighborhood()).orElse(addressEntity.getNeighborhood()))
                .build();
    }


    public List<AddressResponse> toAddressResponse(List<AddressEntity> addresses) {
        return addresses.stream()
                .map(address -> AddressResponse.builder()
                        .id(address.getId())
                        .street(address.getStreet())
                        .city(address.getCity())
                        .state(address.getState())
                        .postalCode(address.getPostalCode())
                        .country(address.getCountry())
                        .complement(address.getComplement())
                        .neighborhood(address.getNeighborhood())
                        .userId(address.getUser() != null ? address.getUser().getId() : null)
                        .build())
                .toList();
    }

    public AddressResponse toAddressResponse(AddressEntity address) {
        return AddressResponse.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .complement(address.getComplement())
                .neighborhood(address.getNeighborhood())
                .userId(address.getUser() != null ? address.getUser().getId() : null)
                .build();
    }
}
