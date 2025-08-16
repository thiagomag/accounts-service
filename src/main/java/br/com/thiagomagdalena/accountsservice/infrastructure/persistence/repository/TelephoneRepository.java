package br.com.thiagomagdalena.accountsservice.infrastructure.persistence.repository;

import br.com.thiagomagdalena.accountsservice.infrastructure.persistence.entities.TelephoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface TelephoneRepository extends JpaRepository<TelephoneEntity, Long> {

    Set<TelephoneEntity> findByUserIdAndDeletedTmspIsNull(Long userId);

    Optional<TelephoneEntity> findByIdAndUserIdAndDeletedTmspIsNull(Long telephoneId, Long userId);
}
