package com.example.worktracker.UnitTests;

import static org.assertj.core.api.Assertions.assertThatException;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.worktracker.dto.WorkDto;
import com.example.worktracker.entity.Work;
import com.example.worktracker.exception.NotFoundException;
import com.example.worktracker.mapper.WorkTrackerMapper;
import com.example.worktracker.repository.WorkTrackerRepository;
import com.example.worktracker.service.WorkTrackerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
public class WorkUnitTests {

	@InjectMocks
	WorkTrackerService service;

	@Autowired
	ObjectMapper objMapper;

	@Mock
	WorkTrackerRepository repo;

	@Mock
	WorkTrackerMapper workTrackerMapper;

	Work w = new Work();

	WorkDto wd = new WorkDto();
	List<Work> lw=new ArrayList<Work>();
	
	List<WorkDto> lwd=new ArrayList<WorkDto>();

	@BeforeEach
	void preps() {
		w = Work.builder().description("test").empId("P01").endDate(LocalDate.now()).jiraId("j01")
				.startDate(LocalDate.now()).team("team1").units(1).build();
		wd = WorkDto.builder().description("test").empId("P01").endDate(LocalDate.now()).jiraId("j01")
				.startDate(LocalDate.now()).team("team1").units(1).build();
		
		lw.add(w);
		lwd.add(wd);
	}

	@Test
	void testSaveWorkSuccessfully() throws JsonProcessingException {
		Mockito.when(workTrackerMapper.WorkToWorkDto(repo.save(workTrackerMapper.WorkDtoToWork(wd)))).thenReturn(wd);
		WorkDto savedTask = service.saveWork(wd);
		Assertions.assertEquals(objMapper.writeValueAsString(wd), objMapper.writeValueAsString(savedTask));
	}

	@Test
	void testSaveWorkandGettingException() throws JsonProcessingException {
		wd.setEmpId(" ");
		Mockito.when(workTrackerMapper.WorkToWorkDto(repo.save(workTrackerMapper.WorkDtoToWork(wd))))
				.thenThrow(ConstraintViolationException.class);
		assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy(() -> {
			service.saveWork(wd);
		});
	}

	@Test
	void testGetJiraByIdSuccessfully() throws JsonProcessingException {
		wd.setEmpId("P01");
		Mockito.when(repo.existsByJiraId(Mockito.any(String.class))).thenReturn(true); 
		Mockito.when(workTrackerMapper.WorkToWorkDto(repo.getByJiraId(Mockito.any(String.class)))).thenReturn(wd);
		WorkDto recievedTask=service.getWorkByJiraId("j01");
		Assertions.assertEquals(objMapper.writeValueAsString(wd), objMapper.writeValueAsString(recievedTask));
	}
	
	@Test
	void testGetJiraByIdException() throws JsonProcessingException {
		wd.setEmpId("P01");
		Mockito.when(repo.existsByJiraId(Mockito.any(String.class))).thenReturn(false); 
		assertThatExceptionOfType(NotFoundException.class).isThrownBy(()->{
			service.getWorkByJiraId("j02");
		});
	}
	
	@Test
	void testGetWorkDetailsByEmpId() throws JsonProcessingException {
		
		Mockito.when(repo.existsByEmpId(Mockito.any(String.class))).thenReturn(true);
		Mockito.when(workTrackerMapper.WorkToWorkDto(repo.findByEmpId(Mockito.any(String.class)))).thenReturn(lwd);
		List<WorkDto> recievedTask =service.getWorkByEmpId("P01");
		System.out.println(recievedTask.size());
		System.out.println(lwd.size());
		Assertions.assertEquals(objMapper.writeValueAsString(recievedTask),objMapper.writeValueAsString(lwd));
		Assertions.assertEquals(recievedTask.get(0).getUnits(),lwd.get(0).getUnits());
	}
	
	@Test
	void testGetWorkDetailsByEmpIdException() throws JsonProcessingException {
		Mockito.when(repo.existsByEmpId(Mockito.any(String.class))).thenReturn(false); 
		assertThatExceptionOfType(NotFoundException.class).isThrownBy(()->{
			service.getWorkByEmpId("P03");
		});
	}
	
	@Test
	void testGetAllWork() throws JsonProcessingException {
		Mockito.when(repo.count()).thenReturn((long) 1);
		Mockito.when(workTrackerMapper.WorkToWorkDto(repo.findAll())).thenReturn(lwd);
		List<WorkDto> recievedTask =service.getAllWork();
		Assertions.assertEquals(objMapper.writeValueAsString(recievedTask),objMapper.writeValueAsString(lwd));
	}
	
	@Test
	void testGetAllWorkException() {
		Mockito.when(repo.count()).thenReturn((long)0);
		assertThatExceptionOfType(NotFoundException.class).isThrownBy(()->{
			service.getAllWork();
		});
	}
	
	void testUpdateWork() {
		Mockito.when(repo.existsByJiraId(Mockito.any(String.class))).thenReturn(true);
		Mockito.when(repo.getByJiraId(Mockito.any(String.class))).thenReturn(w);
		
	}
}
