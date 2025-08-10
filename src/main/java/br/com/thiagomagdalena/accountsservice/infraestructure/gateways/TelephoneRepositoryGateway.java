package br.com.thiagomagdalena.accountsservice.infraestructure.gateways;

import br.com.thiagomagdalena.accountsservice.application.gatways.TelephoneGateway;
import br.com.thiagomagdalena.accountsservice.infraestructure.persistence.entities.TelephoneEntity;
import br.com.thiagomagdalena.accountsservice.infraestructure.persistence.repository.TelephoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TelephoneRepositoryGateway implements TelephoneGateway {

    private final TelephoneRepository telephoneRepository;

    @Override
    public TelephoneEntity save(TelephoneEntity entity) {
        return telephoneRepository.save(entity);
    }

    @Override
    public List<TelephoneEntity> findAll() {
        return telephoneRepository.findAll();
    }

    @Override
    public Optional<TelephoneEntity> findById(Long id) {
        return telephoneRepository.findById(id);
    }

    @Override
    public void delete(TelephoneEntity entity) {
        entity.delete();
        telephoneRepository.save(entity);
    }

    @Override
    public boolean existsById(Long id) {
        return telephoneRepository.existsById(id);
    }

    @Override
    public TelephoneEntity update(TelephoneEntity entity) {
        entity.update();
        return telephoneRepository.save(entity);
    }
}