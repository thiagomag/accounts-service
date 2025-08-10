package br.com.thiagomagdalena.accountsservice.infraestructure.gateways;

import br.com.thiagomagdalena.accountsservice.application.gatways.AddressGateway;
import br.com.thiagomagdalena.accountsservice.infraestructure.persistence.entities.AddressEntity;
import br.com.thiagomagdalena.accountsservice.infraestructure.persistence.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AddressRepositoryGateway implements AddressGateway {

    private final AddressRepository addressRepository;

    @Override
    public AddressEntity save(AddressEntity entity) {
        return addressRepository.save(entity);
    }

    @Override
    public List<AddressEntity> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<AddressEntity> findById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public void delete(AddressEntity entity) {
        entity.delete();
        addressRepository.save(entity);
    }

    @Override
    public boolean existsById(Long id) {
        return addressRepository.existsById(id);
    }

    @Override
    public AddressEntity update(AddressEntity entity) {
        entity.update();
        return addressRepository.save(entity);
    }

    @Override
    public List<AddressEntity> findByUserId(Long userId) {
        return addressRepository.findByUserIdAndDeletedTmspIsNull(userId);
    }

    @Override
    public Optional<AddressEntity> findByIdAndUserId(Long addressId, Long userId) {
        return addressRepository.findByIdAndUserIdAndDeletedTmspIsNull(addressId, userId);
    }
}
