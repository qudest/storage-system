package vsu.bd.project.dto;

import lombok.Data;

@Data
public class ProductDto {

    private final Long id;

    private final String name;

    private final String article;

    private final CategoryDto category;

}
