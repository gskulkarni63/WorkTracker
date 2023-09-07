package com.example.worktracker.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class WorkDto {
    @NotBlank(message = "Employee ID is required")
    @Size(max = 255, message = "Employee ID must be less than 255 characters")
    private String empId;

    @NotBlank(message = "JIRA ID is required")
    @Size(max = 255, message = "JIRA ID must be less than 255 characters")
    private String jiraId;

    @NotBlank(message = "Team is required")
    @Size(max = 255, message = "Team name must be less than 255 characters")
    private String team;

    @Size(max = 1000, message = "Description must be less than 1000 characters")
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
