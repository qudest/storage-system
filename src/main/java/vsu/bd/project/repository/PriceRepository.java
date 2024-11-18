package vsu.bd.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.bd.project.entity.PriceEntity;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
}
