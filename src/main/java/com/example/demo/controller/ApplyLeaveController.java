package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.ApplyLeave;
import com.example.demo.model.LeaveDefaults;
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
	
	@PostMapping("/applyLeave")
	public @Valid String instatiatingLeave(@Valid @RequestBody ApplyLeave leaves) throws EmployeeNotFoundException{
		String result = appliedservice.addAppliedLeave(leaves);
		return result;
	}
	
	@GetMapping("/leaveresponses/{ID}")
//	@PreAuthorize("hasRole('MANAGER')")
	public List<ApplyLeave> getAppliedLeavPerTeam(@PathVariable(value="ID")String teamName) throws EmployeeNotFoundException {
		return gettemp.getAppLeavofTeam(teamName);
	}
	
//	@PreAuthorize("hasRole('MANAGER')")
	@PostMapping("/approveleave/{Id}")
	public @Valid String approvingLeave(@PathVariable(value="Id")String empId) throws EmployeeNotFoundException {
		LeaveDefaults lev = new LeaveDefaults();
		String result="";
		result = applyleaveservice.approveLeave(empId, lev);
		return result;
	}
}
