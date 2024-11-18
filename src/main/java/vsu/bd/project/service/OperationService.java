package vsu.bd.project.service;

import org.springframework.stereotype.Service;
import vsu.bd.project.dto.OperationDto;
import vsu.bd.project.mapper.OperationMapper;
import vsu.bd.project.repository.OperationRepository;

import java.util.List;

@Service
public class OperationService {

    private final OperationRepository repository;
    private final OperationMapper mapper = OperationMapper.INSTANCE;

    public OperationService(OperationRepository repository) {
        this.repository = repository;
    }

    public List<OperationDto> findAll() {
        return mapper.toDto(repository.findAll());
    }

}
