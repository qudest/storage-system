package vsu.bd.project.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductCreationDto {

    @NotEmpty(message = "Article is required")
    @Size(max = 128, message = "Article is too long, max length is 128")
    private String article;

    @NotEmpty(message = "Name is required")
    @Size(max = 128, message = "Name is too long, max length is 128")
    private String name;

    private Long categoryId;

}
