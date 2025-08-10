package br.com.thiagomagdalena.accountsservice.infraestructure.persistence.entities;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

@Table(name = "address")
@Entity(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AddressEntity extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String number;

    private String complement;

    private String neighborhood;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
