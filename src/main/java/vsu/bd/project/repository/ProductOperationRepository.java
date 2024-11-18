package vsu.bd.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.bd.project.entity.ProductOperationEntity;

public interface ProductOperationRepository extends JpaRepository<ProductOperationEntity, Long> {
}
