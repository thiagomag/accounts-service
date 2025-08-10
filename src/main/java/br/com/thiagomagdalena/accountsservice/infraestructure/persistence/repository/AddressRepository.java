package br.com.thiagomagdalena.accountsservice.infraestructure.persistence.repository;

import br.com.thiagomagdalena.accountsservice.infraestructure.persistence.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    List<AddressEntity> findByUserIdAndDeletedTmspIsNull(Long userId);

    Optional<AddressEntity> findByIdAndUserIdAndDeletedTmspIsNull(Long addressId, Long userId);
}
