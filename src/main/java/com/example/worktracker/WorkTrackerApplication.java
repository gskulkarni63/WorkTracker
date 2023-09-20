package com.example.worktracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class WorkTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkTrackerApplication.class, args);
		
	}

}
