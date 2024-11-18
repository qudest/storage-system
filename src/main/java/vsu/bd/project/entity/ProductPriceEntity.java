package vsu.bd.project.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "product_price_hist")
public class ProductPriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_price_hist_id_seq")
    @SequenceGenerator(name = "product_price_hist_id_seq", sequenceName = "product_price_hist_id_seq", allocationSize = 1)
    Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    ProductEntity product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "price_id")
    PriceEntity price;

    @Column(name = "change_date", nullable = false)
    LocalDateTime changeDate;

}
