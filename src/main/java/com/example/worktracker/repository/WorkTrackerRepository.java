package com.example.worktracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.worktracker.entity.Work;

@Repository
public interface WorkTrackerRepository extends JpaRepository<Work, String>{
	Work getByJiraId(String jiraId);
	
	boolean existsByJiraId(String jiraId);
	
	List<Work> findByEmpId(String empId);
	
	boolean existsByEmpId(String jiraId);
}
