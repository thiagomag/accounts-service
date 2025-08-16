package br.com.thiagomagdalena.accountsservice.infrastructure.persistence.repository;

import br.com.thiagomagdalena.accountsservice.infrastructure.persistence.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    Set<AddressEntity> findByUserIdAndDeletedTmspIsNull(Long userId);

    Optional<AddressEntity> findByIdAndUserIdAndDeletedTmspIsNull(Long addressId, Long userId);
}
