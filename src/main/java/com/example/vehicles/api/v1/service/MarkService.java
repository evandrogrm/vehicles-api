package com.example.vehicles.api.v1.service;

import com.example.vehicles.api.v1.controller.filter.MarkFilter;
import com.example.vehicles.api.v1.repository.MarkRepositoryV1;
import com.example.vehicles.api.v1.service.exception.AbstractException;
import com.example.vehicles.api.v1.service.exception.MarkNotFoundException;
import com.example.vehicles.api.v1.service.exception.MarkWithNameAlreadyExistsException;
import com.example.vehicles.api.v1.service.vo.MarkVO;
import com.example.vehicles.domain.Mark;
import com.example.vehicles.mapper.MarkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class MarkService {

    @Autowired
    private MarkRepositoryV1 repository;

    @Autowired
    private MarkMapper mapper;

    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = Exception.class)
    public MarkVO create(MarkVO requestVO) throws AbstractException {
        Optional<Mark> markWithNameAlreadyTaken = Optional.ofNullable(repository.findByName(requestVO.getName()));
        if (markWithNameAlreadyTaken.isPresent()) {
            throw new MarkWithNameAlreadyExistsException(requestVO.getName());
        }

        Mark mark = new Mark()
                .setId(UUID.randomUUID().toString())
                .setName(requestVO.getName());

        mark = repository.save(mark);

        return mapper.map(mark, MarkVO.class);
    }


    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Page<MarkVO> search(MarkFilter markFilter, Pageable pageable) {
        Page<Mark> marks = repository.findAll(markFilter, pageable);
        return mapper.toMarkResponseVOPage(marks);
    }

    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public MarkVO findById(String id) throws AbstractException {
        Mark mark = repository.findById(id)
                .orElseThrow(() -> new MarkNotFoundException(id));
        return mapper.map(mark, MarkVO.class);
    }

    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = Exception.class)
    public void deleteById(String id) throws AbstractException {
        Mark mark = repository.findById(id)
                .orElseThrow(() -> new MarkNotFoundException(id));
        repository.delete(mark);
    }

    @Transactional(value = Transactional.TxType.SUPPORTS)
    public Mark getOrCreateByName(String name) {
        Optional<Mark> mark = Optional.ofNullable(repository.findByName(name));

        if (mark.isPresent()) {
            return mark.get();
        }

        Mark newMark = new Mark()
                .setId(UUID.randomUUID().toString())
                .setName(name);

        return repository.save(newMark);
    }
}
