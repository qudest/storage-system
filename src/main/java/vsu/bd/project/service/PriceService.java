package vsu.bd.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vsu.bd.project.dto.PriceDto;
import vsu.bd.project.mapper.PriceMapper;
import vsu.bd.project.repository.PriceRepository;

@Service
public class PriceService {

    private final PriceRepository repository;
    private final PriceMapper mapper = PriceMapper.INSTANCE;

    public PriceService(PriceRepository repository) {
        this.repository = repository;
    }

    public Page<PriceDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
        return repository.findAll(pageable).map(mapper::toDto);
    }

}
