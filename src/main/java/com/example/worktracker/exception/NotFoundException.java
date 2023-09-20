package com.example.worktracker.exception;

import lombok.Builder;

@Builder
public class NotFoundException extends RuntimeException {

	String field;
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public NotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NotFoundException(String message,String field) {
		super(message);
		// TODO Auto-generated constructor stub
		this.field=field;
	}
	
	public NotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
	
}
