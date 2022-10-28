package com.management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.management.entity.Position;
import com.management.entity.Privilege;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;

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
    @NotNull
    private Collection<Position> positions;
    @NotNull
    private Collection<Privilege> privileges;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate joinedAt;
    @NotNull
    private Integer salary;
    @NotNull
    private String username;
}
