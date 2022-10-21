package com.management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class OneToOneMeetingDto {

    @NotNull
    private Integer initiatorId;
    @NotNull
    private Integer participantId;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    private String subject;
    private String description;
}
