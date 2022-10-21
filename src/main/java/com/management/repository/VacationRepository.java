package com.management.repository;

import com.management.entity.Vacation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacationRepository extends CrudRepository<Vacation, Integer> {
    List<Vacation> findAllByUserId(Integer userId);
}
