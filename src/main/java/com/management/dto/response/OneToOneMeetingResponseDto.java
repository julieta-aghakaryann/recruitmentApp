package com.management.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.management.entity.User;
import com.sun.istack.NotNull;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class OneToOneMeetingResponseDto {

    @NotNull
    private User initiator;
    @NotNull
    private User participant;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    private String subject;
    private String description;
}