package com.example.demo.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Attendance;
import com.example.demo.model.Time;
import com.example.demo.model.WorkingHours;
import com.example.demo.repository.AttendanceRepository;
import com.example.demo.repository.WorkingHoursRepository;
import com.example.demo.service.AddService;


@RequestMapping("/api/v1")
@RestController
public class WorkingHoursController {
	
	@Autowired
	private AddService addservice;
	
	@Autowired
	private AttendanceRepository attendancerepository;
	
	@Autowired
	private WorkingHoursRepository workHoursRepository;
	
	@Autowired
	private MongoTemplate mongotemplate;
	
	@PostMapping("/time")
	public @Valid WorkingHours CreateWorkHours(@Valid @RequestBody WorkingHours workhrs) throws EmployeeNotFoundException {
		String employeeId = workhrs.getId();
		Attendance attendance = attendancerepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + employeeId));
		
		workhrs.setFirstName(attendance.getFirstName());
		addservice.addWorkHrs(workhrs);
		return mongotemplate.save(workhrs);
	}
	
	@PutMapping("/time/{Id}")
	public ResponseEntity<WorkingHours> updateEmployee(@PathVariable(value = "Id") String employeeId) throws EmployeeNotFoundException {
		
		WorkingHours workhours = workHoursRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + employeeId));  
		
		LocalDateTime dat = LocalDateTime.now();
		workhours.setCheckOut(dat);
		LocalDateTime v = workhours.getCheckIn();
		Timestamp timestamp1 = Timestamp.valueOf(dat);
		Timestamp timestamp2 = Timestamp.valueOf(v);
		
		long milliseconds = timestamp1.getTime() - timestamp2.getTime();
		int seconds = (int) milliseconds / 1000;

		int hours = seconds / 3600;
		int minutes = (seconds % 3600) / 60;
		seconds = (seconds % 3600) % 60;
		
		List<Time> statusList = new ArrayList<>();
		statusList.add( new Time(hours,minutes, seconds));
		workhours.setInDetailedTimings(statusList);
		final WorkingHours updatedEmployee = workHoursRepository.save(workhours);

		return ResponseEntity.ok(updatedEmployee);
	}


}
