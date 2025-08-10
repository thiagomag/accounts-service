package br.com.thiagomagdalena.accountsservice.infraestructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@ToString(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<ID> implements Entity<ID> {

    @JsonIgnore
    @CreatedDate
    protected LocalDateTime createdAt;

    @JsonIgnore
    @LastModifiedDate
    protected LocalDateTime updatedAt;

    @JsonIgnore
    protected LocalDateTime deletedTmsp;

    public void delete() {
        setDeletedTmsp(LocalDateTime.now());
    }

    public void update() {
        setUpdatedAt(LocalDateTime.now());
    }
}
