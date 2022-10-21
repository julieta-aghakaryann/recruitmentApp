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
@Table(name = "one_to_one_meeting")
public class OneToOneMeeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer initiatorId;
    private Integer participantId;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    private String subject;
    private String description;
}
