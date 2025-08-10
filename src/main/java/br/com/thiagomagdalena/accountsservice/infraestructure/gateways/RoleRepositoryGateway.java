package br.com.thiagomagdalena.accountsservice.infraestructure.gateways;

import br.com.thiagomagdalena.accountsservice.application.gatways.RoleGateway;
import br.com.thiagomagdalena.accountsservice.infraestructure.persistence.entities.RoleEntity;
import br.com.thiagomagdalena.accountsservice.infraestructure.persistence.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryGateway implements RoleGateway {

    private final RoleRepository roleRepository;

    @Override
    public Optional<RoleEntity> findByRoleName(String name) {
        return roleRepository.findByRoleName(name);
    }

    @Override
    public RoleEntity save(RoleEntity entity) {
        entity.setCreatedAt(LocalDateTime.now());
        return roleRepository.save(entity);
    }

    @Override
    public List<RoleEntity> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<RoleEntity> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public void delete(RoleEntity entity) {
        entity.delete();
        roleRepository.save(entity);
    }

    @Override
    public boolean existsById(Long id) {
        return roleRepository.existsById(id);
    }

    @Override
    public RoleEntity update(RoleEntity entity) {
        entity.update();
        return roleRepository.save(entity);
    }
}
