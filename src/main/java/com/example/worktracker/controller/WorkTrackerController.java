package com.example.worktracker.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.worktracker.dto.WorkDto;
import com.example.worktracker.service.WorkTrackerService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;


@RequestMapping("/api/")
@RequiredArgsConstructor
@RestController
public class WorkTrackerController {
	
	private final WorkTrackerService workTrackerService;
	
    @PostMapping("/")
    public ResponseEntity<WorkDto> createTask(@RequestBody @Valid WorkDto workDto) {
    	WorkDto createdWork = workTrackerService.saveWork(workDto);
        return new ResponseEntity<>(createdWork, HttpStatus.CREATED);
    }

    @GetMapping("jira/{id}")
    public ResponseEntity<WorkDto> getTaskById(@PathVariable("id") @NotBlank(message = "The Id cannot be blank") String id){
    	WorkDto recievedTaskById = workTrackerService.getWorkByJiraId(id);
    	return new ResponseEntity<>(recievedTaskById,HttpStatus.FOUND);
    }
    
    @GetMapping("empId/{empId}")
    public ResponseEntity<List<WorkDto>> getWorkDetailsOfEmpId(@PathVariable("empId") String empId){
    	List<WorkDto> recievedWorkDetailsByEmpId=workTrackerService.getWorkByEmpId(empId);
    	return new ResponseEntity<List<WorkDto>>(recievedWorkDetailsByEmpId,HttpStatus.FOUND);
    }
    
    @GetMapping("getAll")
    public ResponseEntity<List<WorkDto>> getAllWork(){
    	List<WorkDto> recievedWorkDetails = workTrackerService.getAllWork();
    	return new ResponseEntity<List<WorkDto>>(recievedWorkDetails,HttpStatus.FOUND);
    }
    
    
    
//    @DeleteMapping("/{id}")
//    public ResponseEntity<WorkDto> delete( @RequestParam("id") @NotBlank(message = "the id cannot be blank") String id){
//    	if() {
//    		
//    	}
//    }
}
