package br.com.thiagomagdalena.accountsservice.domain.entity;

import br.com.thiagomagdalena.accountsservice.domain.enums.RoleNameEnum;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {

    private RoleNameEnum name;
}