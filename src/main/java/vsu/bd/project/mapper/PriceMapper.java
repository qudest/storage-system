package vsu.bd.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vsu.bd.project.dto.PriceDto;
import vsu.bd.project.entity.PriceEntity;

import java.util.List;

@Mapper
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    PriceDto toDto(PriceEntity priceEntity);

    List<PriceDto> toDto(List<PriceEntity> priceEntities);

}
