package com.management.controller;

import com.management.dto.VacationDto;
import com.management.service.VacationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/company/vacation")
public class VacationController {

    private final VacationService vacationService;

    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getVacation(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok().body(vacationService.getVacationByUserId(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody VacationDto dto) {
        return ResponseEntity.ok().body(vacationService.create(dto));
    }

    @GetMapping("/getRange")
    public ResponseEntity<?> getVacationsBetweenRange(@RequestParam(value = "userId", required = false) Integer userId,
                                                      @RequestParam("dateFrom")
                                                      @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateFrom,
                                                      @RequestParam("dateTo")
                                                          @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateTo) {
        return ResponseEntity.ok().body(vacationService.getVacationsBetweenRange(userId, dateFrom, dateTo));
    }

    @GetMapping("/updateStartDate")
    public ResponseEntity<?> updateVacationStartDate(@RequestParam Integer id,
                                                     @RequestParam("dateFrom")
                                                     @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateFrom) {
        return ResponseEntity.ok().body(vacationService.updateVacationStartDate(id, dateFrom));
    }

    @GetMapping("/updateDueDate")
    public ResponseEntity<?> updateVacationDueDate(@RequestParam Integer id,
                                                   @RequestParam("dateTo")
                                                   @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateTo) {
        return ResponseEntity.ok().body(vacationService.updateVacationDueDate(id, dateTo));
    }
}
