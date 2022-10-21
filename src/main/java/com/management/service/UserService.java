package com.management.service;

import com.management.dto.UserDto;
import com.management.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User createUser(UserDto dto);
    User getUserById(Integer userId);
    User updateSalary(Integer userId, Integer newSalary);
}
