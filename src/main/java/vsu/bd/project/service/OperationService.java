package vsu.bd.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<OperationDto> findAll(int page, int size, String searchValue, String searchColumn) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
        return repository.findAllWithFilters(searchValue, searchColumn, pageable).map(mapper::toDto);
    }

}
