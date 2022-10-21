package com.management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VacationDto {
    public Integer userId;
    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate dateFrom;
    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate dateTo;
    public boolean isPaid;
}
