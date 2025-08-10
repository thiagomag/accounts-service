package br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
public class DeleteAddressRequest {

    private Long addressId;
    private Long userId;
}
