package com.example.worktracker.entity;

import java.time.LocalDateTime;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;


@Entity
@Data
@Builder
public class Work {

    @NotBlank(message = "Employee ID is required")
    @Size(max = 16, message = "Employee ID must be less than 255 characters")
    private String empId;

    @Id
    @NotBlank(message = "JIRA ID is required")
    @Size(max = 16, message = "JIRA ID must be less than 255 characters")
    private String jiraId;

    @NotBlank(message = "Team is required")
    @Size(max = 16, message = "Team name must be less than 255 characters")
    private String team;

    @Size(max = 256, message = "Description must be less than 1000 characters")
    private String description;

    @NotNull(message = "Units field cannot be null")
    @Min(value = 0, message = "Units must be greater than or equal to 0")
    private Integer units;

    @NotNull(message = "Start date field cannot be null")
    @FutureOrPresent(message = "Start date must be in the present or future")
    private LocalDateTime startDate;

    @NotNull(message = "End date field cannot be null")
    @FutureOrPresent(message = "End date must be in the present or future")
    private LocalDateTime endDate;
}
