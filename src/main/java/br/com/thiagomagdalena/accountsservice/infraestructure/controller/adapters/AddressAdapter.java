package br.com.thiagomagdalena.accountsservice.infraestructure.controller.adapters;

import br.com.thiagomagdalena.accountsservice.domain.entity.Address;
import br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.address.AddressDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressAdapter {

    public List<Address> toAddress(List<AddressDto> addressDtos) {
        return addressDtos.stream()
                .map(addressDto -> Address.builder()
                        .street(addressDto.street())
                        .number(addressDto.number())
                        .complement(addressDto.complement())
                        .neighborhood(addressDto.neighborhood())
                        .city(addressDto.city())
                        .state(addressDto.state())
                        .postalCode(addressDto.postalCode())
                        .country(addressDto.country())
                        .build())
                .toList();
    }

    public Address toAddress(AddressDto addressDto) {
        return Address.builder()
                        .street(addressDto.street())
                        .number(addressDto.number())
                        .complement(addressDto.complement())
                        .neighborhood(addressDto.neighborhood())
                        .city(addressDto.city())
                        .state(addressDto.state())
                        .postalCode(addressDto.postalCode())
                        .country(addressDto.country())
                        .build();
    }
}
