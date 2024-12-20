package vsu.bd.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vsu.bd.project.entity.PriceEntity;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p WHERE " +
            "(:searchColumn IS NULL OR " +
            "(:searchColumn = 'type' AND CAST(p.type AS string) LIKE %:searchValue%) OR " +
            "(:searchColumn = 'value' AND CAST(p.value AS string) LIKE %:searchValue%))")
    Page<PriceEntity> findAllWithFilters(@Param("searchValue") String searchValue,
                                         @Param("searchColumn") String searchColumn,
                                         Pageable pageable);

}