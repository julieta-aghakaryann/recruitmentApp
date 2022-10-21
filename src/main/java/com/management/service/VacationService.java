package com.management.service;

import com.management.dto.VacationDto;
import com.management.entity.Vacation;

import java.time.LocalDate;
import java.util.List;

public interface VacationService {

    Vacation create(VacationDto dto);
    Vacation getVacationByUserId(Integer userId);
    List<Vacation> getVacationsBetweenRange(Integer userId, LocalDate dateFrom, LocalDate dateTo);
    Vacation updateVacationStartDate(Integer id, LocalDate dateFrom);
    Vacation updateVacationDueDate(Integer id, LocalDate dateTo);
}
