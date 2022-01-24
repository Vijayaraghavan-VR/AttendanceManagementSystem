package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.EmployeeNotFoundException;

import com.example.demo.model.Attendance;
import com.example.demo.model.LeaveTypes;
import com.example.demo.repository.AttendanceRepository;
import com.example.demo.repository.LeaveRepository;

@RequestMapping("/api/v1")
@RestController
public class LeaveController {
	
	@Autowired
	private AttendanceRepository attendancerepository;
	
	@Autowired
	private LeaveRepository leaverepository;

	
	@GetMapping("/report/{Id}")
	public String employeeLeaveReport (@PathVariable(value = "Id") String employeeId) throws EmployeeNotFoundException {
		Attendance attendance = attendancerepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + employeeId));
		LeaveTypes leav = leaverepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + employeeId));
		int two = 2;
		int one = 1;
		int four = 4;
		int n1 = 2 - leav.getPaidLeave();
		int n2 = 4 - leav.getMedicalLeave();
		int n3 = 1 - leav.getPrivilegeLeave();
	    int n4 = 2 - leav.getLossOfPay();
	    int totalleave = n1+n2+n3+n4;
	    int remainleaves = 9 - totalleave;
		
		return ("Employee ID       : "+employeeId+ '\n'+"First Name        : "+attendance.getFirstName()+'\n'+"Total Leave Taken : "+totalleave+'\n'+"Paid Leave        : "+ (two-leav.getPaidLeave())+'\n'+"Medical Leave     : "
				+ (four-leav.getMedicalLeave())+'\n'+"Privilege Leave   : "+ (one-leav.getPrivilegeLeave())+'\n'+ "LOP               : "+(two-leav.getLossOfPay())+'\n'
				+ "Remaining Leaves  : "+remainleaves);
		
	}
	
	@GetMapping("/leaveTaken/{Id}")
	public String leaveTaken (@PathVariable(value="Id") String employeeId) throws EmployeeNotFoundException{
		Attendance attendance = attendancerepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + employeeId));
		LeaveTypes leav = leaverepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + employeeId));
		int n1 = 2 - leav.getPaidLeave();
		int n2 = 4 - leav.getMedicalLeave();
		int n3 = 1 - leav.getPrivilegeLeave();
	    int n4 = 2 - leav.getLossOfPay();
	    int totalleave = n1+n2+n3+n4;
	    return ("Employee ID         : "+employeeId+'\n'+"First Name          : "+attendance.getFirstName()+'\n'+"Total leave Applied : "+totalleave);
		
	}
	
	@GetMapping("/remainingLeaves/{Id}")
	public String remainingLeave (@PathVariable(value="Id") String employeeId) throws EmployeeNotFoundException{
		Attendance attendance = attendancerepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + employeeId));
		LeaveTypes leav = leaverepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + employeeId));
		int n1 = 2 - leav.getPaidLeave();
		int n2 = 4 - leav.getMedicalLeave();
		int n3 = 1 - leav.getPrivilegeLeave();
	    int n4 = 2 - leav.getLossOfPay();
	    int totalleave = n1+n2+n3+n4;
	    int remainleaves = 9 - totalleave;
	    return ("Employee ID      : "+employeeId+'\n'+"First Name       : "+attendance.getFirstName()+'\n'+"Remaining Leaves : "+remainleaves);	
	}
	
	

}
