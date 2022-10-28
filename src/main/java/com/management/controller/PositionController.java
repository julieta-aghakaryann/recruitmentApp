package com.management.controller;

import com.management.dto.PositionDto;
import com.management.service.PositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("company/position")
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPosition(@RequestBody PositionDto dto) {
        return ResponseEntity.ok().body(positionService.create(dto));
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> findAllPositions() {
        return ResponseEntity.ok().body(positionService.getAllPositions());
    }
}
