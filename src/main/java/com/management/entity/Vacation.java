package com.management.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vacation")
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public Integer userId;
    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate dateFrom;
    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate dateTo;
    public boolean isPaid;
}
