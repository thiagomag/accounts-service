package br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.address;

import br.com.thiagomagdalena.accountsservice.domain.entity.Address;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
public class AddAddressRequest {

    private List<Address> addresses;
    private Long userId;
}
