package br.com.thiagomagdalena.accountsservice.infraestructure.controller.dto;

import br.com.thiagomagdalena.accountsservice.domain.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private String cpf;
    private List<TelephoneResponse> telephones;
    private List<AddressResponse> addresses;
    private List<Role> role;
}
