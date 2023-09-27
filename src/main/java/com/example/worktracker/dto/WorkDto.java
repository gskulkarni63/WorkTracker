package com.example.worktracker.dto;

import java.time.LocalDate;

import com.example.worktracker.validation.StartAndEndDateConstraint;

import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@StartAndEndDateConstraint
public class WorkDto {
	@NotNull
    @NotBlank(message = "Employee ID is required")
    @Size(max = 255, message = "Employee ID must be less than 255 characters")
    private String empId;

	@Id
	@NotNull
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

//    @NotNull(message = "Start date field cannot be null")
//    @FutureOrPresent(message = "Start date must be in the present or future")
    private LocalDate startDate;

//    @NotNull(message = "End date field cannot be null")
//    @FutureOrPresent(message = "End date must be in the present or future")
    private LocalDate endDate;
}
