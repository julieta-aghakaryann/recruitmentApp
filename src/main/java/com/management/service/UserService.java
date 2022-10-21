package com.management.service;

import com.management.dto.UserDto;
import com.management.entity.User;

public interface UserService {
    User createUser(UserDto dto);
    User getUserById(Integer userId);
    User updateSalary(Integer userId, Integer newSalary);
}
