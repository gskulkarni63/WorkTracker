package com.example.worktracker.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.worktracker.dto.WorkDto;
import com.example.worktracker.entity.Work;

@Mapper(componentModel = "spring")
public interface WorkTrackerMapper {	
	public WorkDto WorkToWorkDto(Work w);
	
	public Work WorkDtoToWork(WorkDto w);
	
	public List<Work> WorkDtoToWork(List<WorkDto> w);
	
	public List<WorkDto> WorkToWorkDto(List<Work> w);
	
}
