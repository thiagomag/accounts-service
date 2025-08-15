package br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto.telephone;

import br.com.thiagomagdalena.accountsservice.domain.entity.Telephone;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
public class UpdateTelephoneRequest {

    private Telephone telephone;
    private Long userId;
    private Long telephoneId;
}
