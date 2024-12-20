package vsu.bd.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vsu.bd.project.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    boolean existsByArticle(String article);

    @Query("SELECT p FROM ProductEntity p WHERE " +
            "(:searchColumn IS NULL OR " +
            "(:searchColumn = 'article' AND p.article LIKE %:searchValue%) OR " +
            "(:searchColumn = 'name' AND p.name LIKE %:searchValue%) OR " +
            "(:searchColumn = 'category' AND p.category.name LIKE %:searchValue%))")
    Page<ProductEntity> findAllWithFilters(@Param("searchValue") String searchValue,
                                           @Param("searchColumn") String searchColumn,
                                           Pageable pageable);

}
