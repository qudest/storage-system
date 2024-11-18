package vsu.bd.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import vsu.bd.project.dto.ProductCreationDto;
import vsu.bd.project.dto.ProductDto;
import vsu.bd.project.entity.ProductEntity;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto toDto(ProductEntity productEntity);

    List<ProductDto> toDto(List<ProductEntity> productEntities);

    ProductEntity toEntity(ProductCreationDto productDto);

    @Mapping(source = "category.id", target = "categoryId")
    ProductCreationDto toCreationDto(ProductDto productEntity);

}
