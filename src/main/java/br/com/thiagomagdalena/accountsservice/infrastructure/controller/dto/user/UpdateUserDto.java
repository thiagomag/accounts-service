package br.com.thiagomagdalena.accountsservice.infrastructure.controller.dto.user;

import br.com.thiagomagdalena.accountsservice.domain.enums.EmployeeTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UpdateUserDto {

    private Long userId;
    private String email;
    private String password;
    private String fullName;
    private EmployeeTypeEnum employeeTypeEnum;
    private String councilNumber;
    private String councilState;
    private String specialty;
    private Boolean passwordChangeAuthorized;
    private Boolean emailChangeAuthorized;
}
