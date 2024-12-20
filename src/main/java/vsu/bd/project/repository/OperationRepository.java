package vsu.bd.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vsu.bd.project.entity.OperationEntity;

public interface OperationRepository extends JpaRepository<OperationEntity, Long> {

    @Query("SELECT o FROM OperationEntity o WHERE " +
            "(:searchColumn IS NULL OR " +
            "(:searchColumn = 'date' AND CAST(o.date AS string) LIKE %:searchValue%) OR " +
            "(:searchColumn = 'type' AND CAST(o.type AS string) LIKE %:searchValue%))")
    Page<OperationEntity> findAllWithFilters(@Param("searchValue") String searchValue,
                                             @Param("searchColumn") String searchColumn,
                                             Pageable pageable);

}