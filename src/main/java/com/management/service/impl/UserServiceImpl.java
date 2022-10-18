package com.management.service.impl;

import com.management.dto.UserDto;
import com.management.entity.User;
import com.management.repository.UserRepository;
import com.management.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper = new ModelMapper();
    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User createUser(UserDto dto) {
        User user = modelMapper.map(dto, User.class);
        User save = userRepository.save(user);
        System.out.println(save);
        System.out.println(user);
        return save;
    }

    @Override
    @Transactional
    public User getUserById(Integer userId) {
        return userRepository.findById(1).orElse(new User());
    }
}
