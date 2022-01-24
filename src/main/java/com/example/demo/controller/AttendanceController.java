package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Attendance;
import com.example.demo.repository.AttendanceRepository;
import com.example.demo.service.AddService;
import com.example.demo.service.GetEmployeeService;


@RequestMapping("/api/v1")
@RestController
public class AttendanceController {
	
		@Autowired
		private AddService addservice;
	
		@Autowired
		private GetEmployeeService getempservice;
		
		@Autowired
		private AttendanceRepository attendanceRepository;
		
		@Autowired
		private MongoTemplate mongotemplate;
		
		@PostMapping("/employee")
		public  Attendance CreateEmployee(@Valid @RequestBody Attendance attendance) {
			
			String tea = attendance.getTeam();
			String prefix="_S";
			String a = "AnjaliSweets";
			String b = "BharathiSweets";
			String c = "ChenthurSweets";
			if(tea.equalsIgnoreCase(a)) {
				prefix = "AS";
				
			}
			else if(tea.equalsIgnoreCase(b)) {
				prefix = "BS";
			}
			else if(tea.equalsIgnoreCase(c)){
				prefix = "CS";
			}
			addservice.addEmployee(attendance, prefix);
			return mongotemplate.save(attendance);
		}
		
		
		@GetMapping("/employee")
//		@PreAuthorize("hasRole('ADMIN')")
		public List<Attendance> getEmployees() throws EmployeeNotFoundException {
			return getempservice.viewEmployees();
		}
		
		@GetMapping("/employee/{Id}")
		public Attendance getemployeeByID(@PathVariable String Id) throws EmployeeNotFoundException {
			return getempservice.getByEmployeeId(Id);

		}
		
		@PutMapping("/employee/{Id}")
		public ResponseEntity<Attendance> updateEmployee(@PathVariable(value = "Id") String employeeId,
				@Valid @RequestBody Attendance employeeDetails) throws EmployeeNotFoundException {
			Attendance attendance = attendanceRepository.findById(employeeId)
					.orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + employeeId));
	                
	        attendance.setTeam(employeeDetails.getTeam());
	        attendance.setTerminatedDate(employeeDetails.getTerminatedDate());
	        attendance.setJoiningDate(employeeDetails.getJoiningDate());
	        attendance.setStatus(employeeDetails.getStatus());
	        attendance.setExperience(employeeDetails.getExperience());
	        attendance.setDob(employeeDetails.getDob());
	        attendance.setPhoneNumber(employeeDetails.getPhoneNumber());
			attendance.setEmailId(employeeDetails.getEmailId());
			attendance.setLastName(employeeDetails.getLastName());
			attendance.setFirstName(employeeDetails.getFirstName());
			final Attendance updatedEmployee = attendanceRepository.save(attendance);
			return ResponseEntity.ok(updatedEmployee);
		}
	      
		
		@PutMapping("employee/delete/{Id}")
		public ResponseEntity<Attendance> deletingEmployee(@PathVariable(value = "Id") String employeeId)
        		throws EmployeeNotFoundException {
		Attendance attendance = attendanceRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + employeeId));
                attendance.setTerminatedDate(LocalDate.now());                
                String st = "Inactive";
                attendance.setStatus(st);	
                mongotemplate.save(attendance);
		        return ResponseEntity.ok(attendance);
		}
		
		@GetMapping("/getTeamCount/{Id}")
		public String getTeamsCount(@PathVariable String Id) {
			int n = addservice.getTeamCount(Id);
			return ("Employees in Team "+Id+" is "+n+".");
		}
		
		@GetMapping("/getEmpsOfTeam/{Id}")
		public List<Attendance> getEmpOfPerTeam(@PathVariable(value = "Id")String teamName) throws EmployeeNotFoundException {
			return getempservice.getEmpsofTeam(teamName);
		}
}
