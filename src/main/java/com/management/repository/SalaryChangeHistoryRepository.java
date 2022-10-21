package com.management.repository;

import com.management.entity.SalaryChangeHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryChangeHistoryRepository extends CrudRepository<SalaryChangeHistory, Integer> {
}