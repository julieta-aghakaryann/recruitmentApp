package com.management.service.impl;

import com.management.dto.PositionDto;
import com.management.entity.Position;
import com.management.exception.DuplicatePositionException;
import com.management.repository.PositionRepository;
import com.management.service.PositionService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PositionServiceImpl implements PositionService {

    private final ModelMapper modelMapper = new ModelMapper();
    private final PositionRepository positionRepository;

    public PositionServiceImpl(PositionRepository positionRepository) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.positionRepository = positionRepository;
    }

    @Override
    @Transactional
    public Position create(PositionDto dto){
        if(positionRepository.findByPosition(dto.getPosition())!=null) {
            throw new DuplicatePositionException(dto.getPosition());
        }
        Position position = modelMapper.map(dto, Position.class);

        return positionRepository.save(position);
    }
}
