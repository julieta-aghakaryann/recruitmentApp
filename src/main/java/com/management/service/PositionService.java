package com.management.service;

import com.management.dto.PositionDto;
import com.management.entity.Position;

import java.util.List;

public interface PositionService {
    Position create(PositionDto dto);
    List<Position> getAllPositions();
}
