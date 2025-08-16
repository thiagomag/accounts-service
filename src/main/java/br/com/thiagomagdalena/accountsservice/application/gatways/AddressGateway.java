package br.com.thiagomagdalena.accountsservice.application.gatways;

import br.com.thiagomagdalena.accountsservice.infrastructure.persistence.entities.AddressEntity;

import java.util.Optional;
import java.util.Set;

public interface AddressGateway extends BaseGateway<AddressEntity, Long> {

    Set<AddressEntity> findByUserId(Long userId);

    Optional<AddressEntity> findByIdAndUserId(Long addressId, Long userId);
}
