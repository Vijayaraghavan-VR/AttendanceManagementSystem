package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.LeaveDefaults;
import com.example.demo.model.LeaveTypes;
import com.example.demo.repository.LeaveRepository;
import com.example.demo.service.ApplyLeaveService;

@RequestMapping("/api/v1")
@RestController
public class ApplyLeaveController {
	
	@Autowired
	private ApplyLeaveService applyleaveservice;
	
	@Autowired
	private LeaveRepository levrepository;
	
	@PostMapping("/applyLeave")
	public @Valid String InstatiatingLeave(@Valid @RequestBody LeaveTypes leaves) throws EmployeeNotFoundException{
		
		String employeeId = leaves.getId();
		LeaveDefaults lev = new LeaveDefaults();
		String reason = leaves.getReason();
		String result = applyleaveservice.applyleave(employeeId, reason, leaves, lev);
		return result;
	}
	
	@PutMapping("/applyLeave")
	public @Valid String ForthComingLeaves(@Valid @RequestBody LeaveTypes levtypes) throws EmployeeNotFoundException {
		String employeeId = levtypes.getId();
		String reason = levtypes.getReason();
		LeaveTypes levtype = levrepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + employeeId));
		String result = applyleaveservice.applyleave(employeeId, reason, levtype);
		return result;
	}
	

}
