package vsu.bd.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.bd.project.entity.ProductPriceEntity;

public interface ProductPriceRepository extends JpaRepository<ProductPriceEntity, Long> {
}
