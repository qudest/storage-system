package vsu.bd.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vsu.bd.project.dto.CategoryCreationDto;
import vsu.bd.project.dto.CategoryDto;
import vsu.bd.project.entity.CategoryEntity;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto toDto(CategoryEntity categoryEntity);

    List<CategoryDto> toDto(List<CategoryEntity> categoryEntities);

    CategoryEntity toEntity(CategoryCreationDto categoryCreationDto);

    CategoryCreationDto toCreationDto(CategoryDto productEntity);
}
