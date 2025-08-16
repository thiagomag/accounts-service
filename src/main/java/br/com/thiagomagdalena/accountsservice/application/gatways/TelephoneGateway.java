package br.com.thiagomagdalena.accountsservice.application.gatways;

import br.com.thiagomagdalena.accountsservice.infrastructure.persistence.entities.TelephoneEntity;

import java.util.Optional;
import java.util.Set;

public interface TelephoneGateway extends BaseGateway<TelephoneEntity, Long> {

    Set<TelephoneEntity> findByUserId(Long userId);

    Optional<TelephoneEntity> findByIdAndUserId(Long telephoneId, Long userId);
}
