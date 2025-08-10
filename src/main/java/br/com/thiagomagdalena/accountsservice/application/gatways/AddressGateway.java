package br.com.thiagomagdalena.accountsservice.application.gatways;

import br.com.thiagomagdalena.accountsservice.infraestructure.persistence.entities.AddressEntity;

import java.util.List;
import java.util.Optional;

public interface AddressGateway extends BaseGateway<AddressEntity, Long> {

    List<AddressEntity> findByUserId(Long userId);

    Optional<AddressEntity> findByIdAndUserId(Long addressId, Long userId);
}
