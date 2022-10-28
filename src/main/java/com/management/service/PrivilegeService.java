package com.management.service;

import com.management.dto.PrivilegeDto;
import com.management.entity.Privilege;

import java.util.List;

public interface PrivilegeService {
    Privilege create(PrivilegeDto dto);
    List<Privilege> getAllPrivileges();
}
