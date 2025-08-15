package br.com.thiagomagdalena.accountsservice.infraestructure.persistence.repository;

import br.com.thiagomagdalena.accountsservice.infraestructure.persistence.entities.TelephoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TelephoneRepository extends JpaRepository<TelephoneEntity, Long> {

    List<TelephoneEntity> findByUserIdAndDeletedTmspIsNull(Long userId);

    Optional<TelephoneEntity> findByIdAndUserIdAndDeletedTmspIsNull(Long telephoneId, Long userId);
}
