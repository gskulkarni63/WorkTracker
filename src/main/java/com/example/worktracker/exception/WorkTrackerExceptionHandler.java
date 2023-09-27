package com.example.worktracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class WorkTrackerExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
        	String fieldname = ((FieldError) error).getField();
        	String errorMessage = error.getDefaultMessage();
        	errors.put(fieldname, errorMessage);
        });
        ErrorResponse errorResponse = new ErrorResponse("Validation Failed", errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

	
	@ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleValidationExceptions(MissingServletRequestParameterException ex) {
        Map<String, String> errors = new HashMap<>();
        String paramName = ex.getParameterName();
        String paramMessage=ex.getMessage();
        
        errors.put(paramName, paramMessage);
        
        ErrorResponse errorResponse = new ErrorResponse("Validation Failed", errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleValidationExceptions(NotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        String paramMessage = ex.getMessage();
        String paramField=ex.getField();
        errors.put(paramField, paramMessage);
        ErrorResponse errorResponse = new ErrorResponse("errors", errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
	
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

        for (ConstraintViolation<?> violation : violations) {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            
            // Check if the field name is empty (class-level error)
            if (fieldName.isEmpty()) {
                fieldName = "global"; // Provide a generic key
            }

            errors.put(fieldName, errorMessage);
        }

        ErrorResponse errorResponse = new ErrorResponse("errors", errors);
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
	
}
