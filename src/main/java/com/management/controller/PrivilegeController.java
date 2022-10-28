package com.management.controller;

import com.management.dto.PrivilegeDto;
import com.management.service.PrivilegeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("company/privilege")
public class PrivilegeController {

    private final PrivilegeService privilegeService;

    public PrivilegeController(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPosition(@RequestBody PrivilegeDto dto) {
        return ResponseEntity.ok().body(privilegeService.create(dto));
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllPrivileges() {
        return ResponseEntity.ok().body(privilegeService.getAllPrivileges());
    }
}
