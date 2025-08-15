package br.com.thiagomagdalena.accountsservice.infraestructure.gateways.adapters;

import br.com.thiagomagdalena.accountsservice.domain.entity.Address;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.address.AddressResponse;
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
        Optional.ofNullable(address.getStreet()).ifPresent(addressEntity::setStreet);
        Optional.ofNullable(address.getNumber()).ifPresent(addressEntity::setNumber);
        Optional.ofNullable(address.getCity()).ifPresent(addressEntity::setCity);
        Optional.ofNullable(address.getState()).ifPresent(addressEntity::setState);
        Optional.ofNullable(address.getPostalCode()).ifPresent(addressEntity::setPostalCode);
        Optional.ofNullable(address.getCountry()).ifPresent(addressEntity::setCountry);
        Optional.ofNullable(address.getComplement()).ifPresent(addressEntity::setComplement);
        Optional.ofNullable(address.getNeighborhood()).ifPresent(addressEntity::setNeighborhood);
        return addressEntity;
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
