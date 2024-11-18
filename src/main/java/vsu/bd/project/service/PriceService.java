package vsu.bd.project.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vsu.bd.project.dto.PriceDto;
import vsu.bd.project.mapper.PriceMapper;
import vsu.bd.project.repository.PriceRepository;

import java.util.List;

@Service
public class PriceService {

    private final PriceRepository repository;
    private final PriceMapper mapper = PriceMapper.INSTANCE;

    public PriceService(PriceRepository repository) {
        this.repository = repository;
    }

    public List<PriceDto> findAll() {
        return mapper.toDto(repository.findAll(Sort.by(Sort.Direction.ASC, "id")));
    }

}
