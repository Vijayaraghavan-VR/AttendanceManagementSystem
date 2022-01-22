package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.ApplyLeave;
//import com.example.demo.model.Attendance;
import com.example.demo.model.LeaveDefaults;
import com.example.demo.model.LeaveTypes;
import com.example.demo.repository.ApplyLeaveRepository;
import com.example.demo.repository.LeaveRepository;
import com.example.demo.service.ApplyLeaveResponseService;
import com.example.demo.service.ApplyLeaveService;
import com.example.demo.service.GetEmployeeService;

@RequestMapping("/api/v1")
@RestController
public class ApplyLeaveController {
	
	@Autowired
	private ApplyLeaveService applyleaveservice;
	
	@Autowired
	private ApplyLeaveResponseService appliedservice;
	
	@Autowired
	private GetEmployeeService gettemp;
	
	@Autowired
	private LeaveRepository levrepository;
	
	@Autowired
	private ApplyLeaveRepository applylevrepo;
	
	@PostMapping("/applyLeave")
	public @Valid String InstatiatingLeave(@Valid @RequestBody ApplyLeave leaves) throws EmployeeNotFoundException{
	
		
//		String employeeId = leaves.getId();
//		LeaveDefaults lev = new LeaveDefaults();
//		String reason = leaves.getReason();
		
		String result = appliedservice.addAppliedLeave(leaves);
		
//		String result = applyleaveservice.applyleave(employeeId, reason, leaves, lev);
		return result;
	}
	
	@GetMapping("/leaveresponses/{ID}")
	public List<ApplyLeave> getAppliedLeavPerTeam(@PathVariable(value="ID")String teamName) throws EmployeeNotFoundException {
		return gettemp.getAppLeavofTeam(teamName);
	}
	
	@PostMapping("/approveleave/{Id}")
	public @Valid String ApprovingLeave(@PathVariable(value="Id")String empId) throws EmployeeNotFoundException {
		
		ApplyLeave levtype = applylevrepo.findById(empId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + empId));
		LeaveDefaults lev = new LeaveDefaults();
		String reason = levtype.getReason();
		String result = applyleaveservice.applyleave(empId, reason, leaves, lev);
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
