package com.example.worktracker.validation;

import java.time.LocalDate;

import com.example.worktracker.entity.Work;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StartAndEndDateValidator implements ConstraintValidator<StartAndEndDateConstraint, Work> {

	@Override
	public void initialize(StartAndEndDateConstraint constraintAnnotation) {
		// TODO Auto-generated method stub
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(Work work, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if (work == null) {
			return true; // Null values are validated separately with @NotNull
		}

		LocalDate startDate = work.getStartDate();
		LocalDate endDate = work.getEndDate();

		if (startDate == null || endDate == null) {
			return true; // If either date is null, validation should pass
		}

		LocalDate currentDate = LocalDate.now();

		// Ensure that the start date is in the past or present
		boolean isStartDateValid = !startDate.isAfter(currentDate);

		// Ensure that the end date is in the future of the start date
		boolean isEndDateValid = endDate.isAfter(startDate) || endDate.isEqual(startDate);

		return isEndDateValid && isStartDateValid;
	}

}
