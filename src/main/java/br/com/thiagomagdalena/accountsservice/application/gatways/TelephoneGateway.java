package br.com.thiagomagdalena.accountsservice.application.gatways;

import br.com.thiagomagdalena.accountsservice.infraestructure.persistence.entities.TelephoneEntity;

import java.util.List;
import java.util.Optional;

public interface TelephoneGateway extends BaseGateway<TelephoneEntity, Long> {

    List<TelephoneEntity> findByUserId(Long userId);

    Optional<TelephoneEntity> findByIdAndUserId(Long telephoneId, Long userId);
}
