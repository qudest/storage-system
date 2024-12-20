package vsu.bd.project.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vsu.bd.project.dto.CategoryCreationDto;
import vsu.bd.project.dto.CategoryDto;
import vsu.bd.project.entity.CategoryEntity;
import vsu.bd.project.mapper.CategoryMapper;
import vsu.bd.project.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper = CategoryMapper.INSTANCE;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Page<CategoryDto> findAll(int page, int size, String searchValue, String searchColumn) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
        return repository.findAllWithFilters(searchValue, searchColumn, pageable).map(mapper::toDto);
    }

    public List<CategoryDto> findAll() {
        return mapper.toDto(repository.findAll(Sort.by(Sort.Direction.ASC, "id")));
    }

    public CategoryEntity findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    public CategoryDto findById(Long id) {
        return mapper.toDto(findEntityById(id));
    }

    public CategoryCreationDto toCreationDto(CategoryDto categoryDto) {
        return mapper.toCreationDto(categoryDto);
    }

    public void save(CategoryCreationDto category) {
        repository.save(mapper.toEntity(category));
    }

    public void update(Long id, CategoryCreationDto category) {
        CategoryEntity entity = findEntityById(id);
        entity.setName(category.getName());
        repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
