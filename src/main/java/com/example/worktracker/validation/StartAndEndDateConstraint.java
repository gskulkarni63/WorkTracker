package com.example.worktracker.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StartAndEndDateValidator.class)
@Documented
public @interface StartAndEndDateConstraint {
	String message() default "Invalid date range";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
