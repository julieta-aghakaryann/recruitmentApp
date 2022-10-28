package com.management.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrivilegeDto {
    @NotNull
    private String privilege;
}
