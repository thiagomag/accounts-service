package br.com.thiagomagdalena.accountsservice.infraestructure.persistence.repository;

import br.com.thiagomagdalena.accountsservice.infraestructure.persistence.entities.TelephoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelephoneRepository extends JpaRepository<TelephoneEntity, Long> {
}
