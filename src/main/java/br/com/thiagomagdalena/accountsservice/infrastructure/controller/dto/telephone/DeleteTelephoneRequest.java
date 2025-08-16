package br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.telephone;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
public class DeleteTelephoneRequest {

    private Long telephoneId;
    private Long userId;
}
