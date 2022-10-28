package com.management.service.impl;

import com.management.exception.WrongEmailException;
import com.management.dto.UserDto;
import com.management.entity.Position;
import com.management.entity.SalaryChangeHistory;
import com.management.entity.User;
import com.management.repository.PositionRepository;
import com.management.repository.PrivilegeRepository;
import com.management.repository.SalaryChangeHistoryRepository;
import com.management.repository.UserRepository;
import com.management.service.UserService;
import com.management.validation.EmailValidation;
import com.sun.istack.NotNull;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper = new ModelMapper();
    private final UserRepository userRepository;
    private final SalaryChangeHistoryRepository salaryChangeHistoryRepository;
    private final PositionRepository positionRepository;
    private final PrivilegeRepository privilegeRepository;


    public UserServiceImpl(UserRepository userRepository,
                           SalaryChangeHistoryRepository salaryChangeHistoryRepository, PositionRepository positionRepository, PrivilegeRepository privilegeRepository) {
        this.salaryChangeHistoryRepository = salaryChangeHistoryRepository;
        this.positionRepository = positionRepository;
        this.privilegeRepository = privilegeRepository;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User createUser(UserDto dto) {
        User user = modelMapper.map(dto, User.class);
        user.setPositions(dto.getPositions());
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


    @Transactional
    public User findByUsername(@NotNull String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(@NotNull String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Username [%s] not found", user.getUsername()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getPositions()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Position> positions) {
        return positions.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }
}
