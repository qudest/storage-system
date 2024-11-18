package vsu.bd.project.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "product_operation")
public class ProductOperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_operation_id_seq")
    @SequenceGenerator(name = "product_operation_id_seq", sequenceName = "product_operation_id_seq", allocationSize = 1)
    Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    ProductEntity product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "operation_id")
    OperationEntity operation;

    @Column(nullable = false)
    Integer count;

}
