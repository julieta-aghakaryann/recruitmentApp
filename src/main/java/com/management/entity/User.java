package com.management.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String username;
    @JsonProperty(value = "first_name")
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate joinedAt;
    private Integer salary;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", position=" + position +
                ", joinedAt=" + joinedAt +
                ", salary=" + salary +
                '}';
    }
}
