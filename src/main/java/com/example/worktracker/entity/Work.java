package com.example.worktracker.entity;

import java.time.LocalDate;


//import javax.validation.constraints.FutureOrPresent;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Work {

    @NotBlank(message = "Employee ID is required")
    @Size(max = 16, message = "Employee ID must be less than 16 characters")
    private String empId;

    @Id
	@NotNull
    @NotBlank(message = "JIRA ID is required")
    @Size(max = 16, message = "JIRA ID must be less than 16 characters")
    private String jiraId;

    @NotBlank(message = "Team is required")
    @Size(max = 16, message = "Team name must be less than 16 characters")
    private String team;

    @Size(max = 256, message = "Description must be less than 256 characters")
    private String description;

    @NotNull(message = "Units field cannot be null")
    @Min(value = 0, message = "Units must be greater than or equal to 0")
    private Integer units;

    @NotNull(message = "Start date field cannot be null")
    private LocalDate startDate;

    @NotNull(message = "End date field cannot be null")
    @FutureOrPresent(message = "End date must be in the present or future")
    private LocalDate endDate;
}
