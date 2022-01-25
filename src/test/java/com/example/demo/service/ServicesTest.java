package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.ResponseEntity;

import com.example.demo.controller.ApplyLeaveController;
import com.example.demo.controller.AttendanceController;
import com.example.demo.controller.LeaveController;
import com.example.demo.controller.WorkingHoursController;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.ApplyLeave;
import com.example.demo.model.Attendance;
//import com.example.demo.model.WorkingHours;

@SpringBootTest
public class ServicesTest {
	
	@Autowired
	private AddService addse;
	
	@Autowired
	private GetEmployeeService getserv;
	
	@Autowired
	private ApplyLeaveResponseService aplserv;
	
//	@Autowired 
//	private ApplyLeaveService apply;
	
	@Autowired
	private AttendanceController att;
	
	@Autowired
	private LeaveController lev;
	
	@Autowired
	private WorkingHoursController workhr;
	
	@Autowired
	private ApplyLeaveController appl;
	
	
	@Test
	public void addEmployeeTest() throws Exception {
		
		long num = 917630487;
		int n = 2;
		LocalDate dob = LocalDate.now();
		Attendance atten = new Attendance("Vijayaraghavan","D","vasjd",num,dob,n,"AnjaliSweets");
		String rs = addse.addEmployee(atten, "AS");
		assertEquals( rs, "Employee Added" );
	}
	
	@Test
	public void getByEmployeeIdTest() throws EmployeeNotFoundException {
		assertTrue(getserv.getByEmployeeId("BS13") instanceof Attendance);	
	}
	
	@Test
	public void CreateEmployeeTest() {
		long num = 917630487;
		int n = 2;
		LocalDate dob = LocalDate.now();
		Attendance atten = new Attendance("Vijayaraghavan","D","vasjd",num,dob,n,"AnjaliSweets");
		assertTrue(att.CreateEmployee(atten) instanceof Attendance);
	}
	
	@Test
	public void getEmployeesTest() throws EmployeeNotFoundException {
		assertTrue(att.getEmployees() instanceof List<?>);
	}
	
	@Test
	public void updateEmployeeTest() throws EmployeeNotFoundException {
		long num = 917630487;
		int n = 2;
		LocalDate dob = LocalDate.now();
		Attendance atten = new Attendance("Vijayaraghavan","D","vasjd",num,dob,n,"AnjaliSweets");
		assertTrue(att.updateEmployee("AS2", atten) instanceof ResponseEntity<?>);
	}
	
	@Test
	public void deletingEmployeeTest() throws EmployeeNotFoundException {
		assertTrue(att.deletingEmployee("BS14") instanceof ResponseEntity<?>);
	}
	
	@Test
	public void getTeamsCountTest() {
		assertTrue(att.getTeamsCount("AS") instanceof String);
	}
	
	@Test
	public void getEmpOfPerTeamTest() throws EmployeeNotFoundException {
		assertTrue(att.getEmpOfPerTeam("AS") instanceof List<?>);
	}
	
	@Test
	public void getTeamCountASTest() {
		assertTrue(Integer.toString(addse.getTeamCount("AS")) instanceof String);
	}
	
	@Test
	public void getTeamsCountBSTest() {
		assertTrue(Integer.toString(addse.getTeamCount("BS")) instanceof String);
	}
	
	@Test
	public void getTeamCountCSTest() {
		assertTrue(Integer.toString(addse.getTeamCount("CS")) instanceof String);
	}
	
	@Test
	public void leaveTakenTest() throws EmployeeNotFoundException {
		assertTrue(lev.leaveTaken("BS14") instanceof String);
	}
	
	@Test
	public void employeeLeaveReportTest() throws EmployeeNotFoundException {
		assertTrue(lev.employeeLeaveReport("BS14") instanceof String);
	}
	
	@Test
	public void remainingLeaveTest() throws EmployeeNotFoundException {
		assertTrue(lev.remainingLeave("BS14") instanceof String);
	}
	
//	@Test
//	public void createWorkHoursTest() throws EmployeeNotFoundException {
//		assertTrue(workhr.createWorkHours("BS14") instanceof WorkingHours);
//	}
	
//	@Test
//	public void addWorkHoursTest() {
//		WorkingHours work = new WorkingHours("BS13",null,null,null, null);
//		assertTrue(addse.addWorkHrs(work) instanceof String);
//	}
	
	@Test
	public void updateEmployeeWorkHoursTest() throws EmployeeNotFoundException {
		assertTrue(workhr.updateEmployeeWorkHours("BS14") instanceof ResponseEntity<?>);
	}
	
	@Test
	public void instatiatingLeaveTest() throws EmployeeNotFoundException{
		
		ApplyLeave app = new ApplyLeave("BS14", null, "ML");
		assertTrue(appl.instatiatingLeave(app) instanceof String);
	}
	
	@Test
	public void getAppliedLeavPerTeamTest() throws EmployeeNotFoundException {
		assertTrue(appl.getAppliedLeavPerTeam("AS") instanceof List<?>);
	}
	
	@Test
	public void approvingLeaveTest() throws EmployeeNotFoundException {
		assertTrue(appl.approvingLeave("BS14") instanceof String);
	}
	
	@Test
	public void addAppliedLeaveTest() throws EmployeeNotFoundException {
		ApplyLeave app = new ApplyLeave("BS14", null, "ML");
		assertTrue(aplserv.addAppliedLeave(app) instanceof String);
	}
	
	@Test
	public void approveLeaveTest() {
		
	}	
}
