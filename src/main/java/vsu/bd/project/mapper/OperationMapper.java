package vsu.bd.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vsu.bd.project.dto.OperationDto;
import vsu.bd.project.entity.OperationEntity;

import java.util.List;

@Mapper
public interface OperationMapper {

    OperationMapper INSTANCE = Mappers.getMapper(OperationMapper.class);

    OperationDto toDto(OperationEntity operationEntity);

    List<OperationDto> toDto(List<OperationEntity> operationEntities);

}
