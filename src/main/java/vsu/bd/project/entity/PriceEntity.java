package vsu.bd.project.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vsu.bd.project.enums.PriceType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "price")
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_id_seq")
    @SequenceGenerator(name = "price_id_seq", sequenceName = "price_id_seq", allocationSize = 1)
    Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    PriceType type;

    @Column(nullable = false)
    Integer value;

}
