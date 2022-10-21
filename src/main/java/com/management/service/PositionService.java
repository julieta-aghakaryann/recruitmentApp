package com.management.service;

import com.management.dto.PositionDto;
import com.management.entity.Position;

public interface PositionService {
    Position create(PositionDto dto);
}
