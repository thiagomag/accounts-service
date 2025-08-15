package br.com.thiagomagdalena.accountsservice.infraestructure.persistence.repository;

import br.com.thiagomagdalena.accountsservice.infraestructure.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT DISTINCT u FROM User u " +
            "LEFT JOIN FETCH u.telephones t " +
            "LEFT JOIN FETCH u.addresses a " +
            "LEFT JOIN FETCH u.userRoles ur " +
            "WHERE u.deletedTmsp IS NULL " +
            "AND (t.id IS NULL OR t.deletedTmsp IS NULL) " +
            "AND (a.id IS NULL OR a.deletedTmsp IS NULL) " +
            "AND (ur.id IS NULL OR ur.deletedTmsp IS NULL) ")
    List<UserEntity> findAllActiveUsersWithActiveRelations();

    @Query("SELECT DISTINCT u FROM User u " +
            "LEFT JOIN FETCH u.telephones t " +
            "LEFT JOIN FETCH u.addresses a " +
            "LEFT JOIN FETCH u.userRoles ur " +
            "WHERE u.deletedTmsp IS NULL " +
            "AND (t.id IS NULL OR t.deletedTmsp IS NULL) " +
            "AND (a.id IS NULL OR a.deletedTmsp IS NULL) " +
            "AND (ur.id IS NULL OR ur.deletedTmsp IS NULL) " +
            "AND u.id = :userId")
    Optional<UserEntity> findActiveUserByIdWithActiveRelations(@Param("userId") Long userId);

}