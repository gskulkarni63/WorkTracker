package com.example.worktracker.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.worktracker.dto.WorkDto;
import com.example.worktracker.entity.Work;
import com.example.worktracker.exception.ErrorResponse;
import com.example.worktracker.exception.NotFoundException;
import com.example.worktracker.mapper.WorkTrackerMapper;
import com.example.worktracker.repository.WorkTrackerRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WorkTrackerService {
	
	
	public final WorkTrackerRepository workTrackerRepository;
	
	public final WorkTrackerMapper workTrackerMapper;

	public WorkDto saveWork(@Valid WorkDto work) {
		return workTrackerMapper.WorkToWorkDto(workTrackerRepository.save(workTrackerMapper.WorkDtoToWork(work)));
	}
	
	public WorkDto getWorkByJiraId(@NotBlank(message = "The Id cannot be blank") String id) {
		if(!workTrackerRepository.existsByJiraId(id)) {	
			throw new NotFoundException("The Jira Id = "+id+" does not exist","Jira Id");
		}
		return workTrackerMapper.WorkToWorkDto(workTrackerRepository.getByJiraId(id));
	}
	
	public List<WorkDto> getWorkByEmpId(@NotBlank(message = "The Id cannot be blank") String empId) {
		if(!workTrackerRepository.existsByEmpId(empId)) {
			throw new NotFoundException("The Employee Id = "+empId+" does not exist", "Emp Id");
		}
		return workTrackerMapper.WorkToWorkDto(workTrackerRepository.findByEmpId(empId));
	}
	
	public List<WorkDto> getAllWork(){
		if(workTrackerRepository.count()==0) {
			throw new NotFoundException("No Records Available","");
		}
		return workTrackerMapper.WorkToWorkDto(workTrackerRepository.findAll());
	}
}
