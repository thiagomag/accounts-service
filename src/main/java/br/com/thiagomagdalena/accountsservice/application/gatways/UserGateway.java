package br.com.thiagomagdalena.accountsservice.application.gatways;

import br.com.thiagomagdalena.accountsservice.infraestructure.persistence.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserGateway extends BaseGateway<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findAll();

}
