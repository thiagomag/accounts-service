package br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.address;

import br.com.thiagomagdalena.accountsservice.domain.entity.Address;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
public class UpdateAddressRequest {

    private Address address;
    private Long userId;
    private Long addressId;
}
