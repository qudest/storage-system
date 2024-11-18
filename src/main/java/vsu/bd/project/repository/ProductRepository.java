package vsu.bd.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.bd.project.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    boolean existsByArticle(String article);

}
