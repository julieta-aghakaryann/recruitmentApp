package com.management.service.impl;

import com.management.dto.VacationDto;
import com.management.entity.Vacation;
import com.management.exception.WrongDateException;
import com.management.repository.VacationRepository;
import com.management.service.VacationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VacationServiceImpl implements VacationService {

    private final ModelMapper modelmapper = new ModelMapper();
    private final VacationRepository vacationRepository;

    public VacationServiceImpl(VacationRepository vacationRepository) {
        modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.vacationRepository = vacationRepository;
    }

    @Override
    @Transactional
    public Vacation create(VacationDto dto) {
        Vacation vacation = modelmapper.map(dto, Vacation.class);
        if(vacation.getDateTo().compareTo(vacation.getDateFrom())<1){
            throw new WrongDateException();
        }
        return vacationRepository.save(vacation);
    }

    @Override
    @Transactional
    public Vacation getVacationByUserId(Integer userId) {
        return vacationRepository.findById(userId).orElse(new Vacation());
    }

    @Override
    @Transactional
    public List<Vacation> getVacationsBetweenRange(Integer userId, LocalDate dateFrom, LocalDate dateTo) {
        List<Vacation> list = new ArrayList<>();
        List<Vacation> vacationList;
        if (userId == null) {
            vacationList = (List<Vacation>) vacationRepository.findAll();
        } else {
            vacationList = vacationRepository.findAllByUserId(userId);
        }
        for (Vacation vacation : vacationList) {
            if (vacation.getDateFrom().compareTo(dateFrom) >= 0 && vacation.getDateTo().compareTo(dateTo) <= 0) {
                list.add(vacation);
            }
        }
        return list;
    }

    @Override
    @Transactional
    public Vacation updateVacationStartDate(Integer id, LocalDate dateFrom) {
        Vacation vacation = vacationRepository.findById(id).orElse(null);
        if(vacation!=null & vacation.getDateTo().compareTo(vacation.getDateFrom())<1){
            throw new WrongDateException();
        }
        vacation.setDateFrom(dateFrom);
        return  vacationRepository.save(vacation);
    }

    @Override
    @Transactional
    public Vacation updateVacationDueDate(Integer id, LocalDate dateTo) {
        Vacation vacation = vacationRepository.findById(id).orElse(null);
        if(vacation!=null & vacation.getDateTo().compareTo(vacation.getDateFrom())<1){
            throw new WrongDateException();
        }
        vacation.setDateFrom(dateTo);
        return vacationRepository.save(vacation);
    }
}
