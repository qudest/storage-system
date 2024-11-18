package vsu.bd.project.dto;

import lombok.Data;
import vsu.bd.project.enums.PriceType;

@Data
public class PriceDto {

    private final Long id;

    private final PriceType type;

    private final Integer value;

}
