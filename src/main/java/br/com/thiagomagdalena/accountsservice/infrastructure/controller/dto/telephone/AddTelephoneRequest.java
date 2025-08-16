package br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.telephone;

import br.com.thiagomagdalena.accountsservice.domain.entity.Telephone;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
public class AddTelephoneRequest {

    private List<Telephone> telephones;
    private Long userId;
}
