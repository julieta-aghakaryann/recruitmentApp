package com.management.service.impl;

import com.management.config.Configurations;
import com.management.exception.WrongEmailException;
import com.management.dto.UserDto;
import com.management.entity.Position;
import com.management.entity.SalaryChangeHistory;
import com.management.entity.User;
import com.management.repository.PositionRepository;
import com.management.repository.SalaryChangeHistoryRepository;
import com.management.repository.UserRepository;
import com.management.service.UserService;
import com.management.validation.EmailValidation;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper = new ModelMapper();
    private final UserRepository userRepository;
    private final SalaryChangeHistoryRepository salaryChangeHistoryRepository;
    private final PositionRepository positionRepository;
    private final Configurations configurations;


    public UserServiceImpl(UserRepository userRepository,
                           SalaryChangeHistoryRepository salaryChangeHistoryRepository,
                           PositionRepository positionRepository,
                           Configurations configurations) {
        this.salaryChangeHistoryRepository = salaryChangeHistoryRepository;
        this.positionRepository = positionRepository;
        this.configurations = configurations;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User createUser(UserDto dto) {
        User user = modelMapper.map(dto, User.class);
        user.setPosition(positionRepository.findByPosition(dto.getPosition()));
        user.setPassword(configurations.encoder().encode(dto.getPassword()));
        if (!new EmailValidation().emailValidation(user.getEmail())) {
            throw new WrongEmailException();
        }

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElse(new User());
    }

    @Override
    @Transactional
    public User updateSalary(Integer userId, Integer newSalary) {
        User user = userRepository.findById(userId).orElse(new User());
        SalaryChangeHistory salaryChangeHistory = new SalaryChangeHistory(userId, user, user.getSalary(), newSalary, null);
        salaryChangeHistoryRepository.save(salaryChangeHistory);
        user.setSalary(newSalary);
        return user;
    }
}
