package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.ApplyLeave;
import com.example.demo.model.Attendance;
import com.example.demo.repository.AttendanceRepository;

@Service
public class ApplyLeaveResponseService {
	
	@Autowired
	private MongoTemplate mongotemplate;
	
	@Autowired
	private AttendanceRepository attendancerepository;
	
	public String addAppliedLeave(ApplyLeave leaves) throws EmployeeNotFoundException {
		String employeeId = leaves.getId();
		Attendance attendance = attendancerepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + employeeId));
		leaves.setFirstName(attendance.getFirstName());
		mongotemplate.save(leaves);
		return "Leave Applied !"+'\n'+"Will be notified soon !";
	}

}
