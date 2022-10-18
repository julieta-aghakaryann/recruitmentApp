package com.management.service;

import com.management.dto.UserDto;
import com.management.entity.User;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserService {
    User createUser(UserDto dto);
    User getUserById(Integer userId);
}
