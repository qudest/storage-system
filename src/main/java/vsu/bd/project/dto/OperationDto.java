package vsu.bd.project.dto;

import lombok.Data;
import vsu.bd.project.enums.OperationType;

import java.time.LocalDateTime;

@Data
public class OperationDto {

    private final String id;

    private final LocalDateTime date;

    private final OperationType type;

}
