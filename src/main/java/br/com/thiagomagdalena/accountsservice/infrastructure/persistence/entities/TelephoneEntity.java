package br.com.thiagomagdalena.accountsservice.infrastructure.persistence.entities;

import br.com.thiagomagdalena.accountsservice.domain.enums.TelephoneTypeEnum;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

@Table(name = "telephones")
@Entity(name = "telephone")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TelephoneEntity extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_code", nullable = false)
    private String countryCode;

    @Column(name = "area_code", nullable = false)
    private String areaCode;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TelephoneTypeEnum type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
