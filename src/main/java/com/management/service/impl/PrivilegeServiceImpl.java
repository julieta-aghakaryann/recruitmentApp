package com.management.service.impl;

import com.management.dto.PrivilegeDto;
import com.management.entity.Privilege;
import com.management.exception.DuplicatePrivilegeException;
import com.management.repository.PrivilegeRepository;
import com.management.service.PrivilegeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    private final ModelMapper modelMapper = new ModelMapper();
    private final PrivilegeRepository privilegeRepository;

    public PrivilegeServiceImpl(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    @Transactional
    public Privilege create(PrivilegeDto dto){
        if(privilegeRepository.findByName(dto.getPrivilege())!=null) {
            throw new DuplicatePrivilegeException(dto.getPrivilege());
        }
        Privilege privilege = modelMapper.map(dto, Privilege.class);

        return privilegeRepository.save(privilege);
    }

    @Override
    public List<Privilege> getAllPrivileges() {
        return (List<Privilege>) privilegeRepository.findAll();
    }
}
