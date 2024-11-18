package vsu.bd.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.bd.project.entity.OperationEntity;

public interface OperationRepository extends JpaRepository<OperationEntity, Long> {
}
