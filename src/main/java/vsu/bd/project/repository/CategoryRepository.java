package vsu.bd.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vsu.bd.project.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query("SELECT c FROM CategoryEntity c WHERE " +
            "(:searchColumn IS NULL OR " +
            "(:searchColumn = 'name' AND c.name LIKE %:searchValue%))")
    Page<CategoryEntity> findAllWithFilters(@Param("searchValue") String searchValue,
                                            @Param("searchColumn") String searchColumn,
                                            Pageable pageable);

}
