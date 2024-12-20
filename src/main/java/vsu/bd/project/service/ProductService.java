package vsu.bd.project.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vsu.bd.project.dto.ProductCreationDto;
import vsu.bd.project.dto.ProductDto;
import vsu.bd.project.entity.CategoryEntity;
import vsu.bd.project.entity.ProductEntity;
import vsu.bd.project.mapper.ProductMapper;
import vsu.bd.project.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final CategoryService categoryService;
    private final ProductMapper mapper = ProductMapper.INSTANCE;

    public ProductService(ProductRepository repository, CategoryService categoryRepository) {
        this.repository = repository;
        this.categoryService = categoryRepository;
    }

    public Page<ProductDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
        return repository.findAll(pageable).map(mapper::toDto);
    }

    public ProductDto findById(Long id) {
        return mapper.toDto(findEntityById(id));
    }

    public ProductEntity findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id)
                );
    }

    public boolean existsByArticle(String article) {
        return repository.existsByArticle(article);
    }

    public ProductCreationDto toCreationDto(ProductDto productDto) {
        return mapper.toCreationDto(productDto);
    }

    public void save(ProductCreationDto product) {
        CategoryEntity category = categoryService.findEntityById(product.getCategoryId());
        ProductEntity entity = mapper.toEntity(product);
        entity.setCategory(category);
        repository.save(entity);
    }

    public void update(Long id, ProductCreationDto product) {
        CategoryEntity category = categoryService.findEntityById(product.getCategoryId());
        ProductEntity entity = mapper.toEntity(product);
        entity.setId(id);
        entity.setCategory(category);
        repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
