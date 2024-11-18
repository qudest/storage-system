package vsu.bd.project.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryCreationDto {

    @NotEmpty(message = "Name is required")
    @Size(max = 128, message = "Name is too long, max length is 128")
    private String name;

}
