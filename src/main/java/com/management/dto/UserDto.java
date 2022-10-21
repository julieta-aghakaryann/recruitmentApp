package com.management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String position;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate joinedAt;
    @NotNull
    private Integer salary;
}
