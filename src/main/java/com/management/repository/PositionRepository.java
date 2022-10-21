package com.management.repository;

import com.management.entity.Position;
import org.springframework.data.repository.CrudRepository;

public interface PositionRepository extends CrudRepository<Position, Integer> {
    Position findByPosition(String position);
}
