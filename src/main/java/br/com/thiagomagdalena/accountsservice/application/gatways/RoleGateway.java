package br.com.thiagomagdalena.accountsservice.application.gatways;

import br.com.thiagomagdalena.accountsservice.infraestructure.persistence.entities.RoleEntity;

import java.util.Optional;

public interface RoleGateway extends BaseGateway<RoleEntity, Long> {

    Optional<RoleEntity> findByRoleName(String name);
}
