package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Attendance;

@Service
public class GetEmployeeService {
	
	@Autowired
	private MongoTemplate mongotemplate;
	
	public List<Attendance> viewEmployees() throws EmployeeNotFoundException {
		try {
			return mongotemplate.findAll(Attendance.class);
		} catch (Exception e) {
			throw new EmployeeNotFoundException("Employees Not Found");
		}
	}
	
	public List<Attendance> getEmpsofTeam(String Id) throws EmployeeNotFoundException{
		
		List<Attendance> attendance = this.viewEmployees();
		List<Attendance> atten = new ArrayList<>();
		for(Attendance attend : attendance) {
			if(Id.equals("AS")) {
				if(attend.getId().contains("AS")) {
					atten.add(attend);
				}
			}
			else if(Id.equals("BS")) {
				if(attend.getId().contains("BS")) {
					atten.add(attend);
				}	
			}
			else if(Id.equals("CS")) {
				if(attend.getId().contains("CS")) {
					atten.add(attend);
				}
			}
		}
		return atten;
	}

	public Attendance getByEmployeeId(String Id) throws EmployeeNotFoundException {
		// TODO Auto-generated method stub
		Attendance attendance = mongotemplate.findById(Id, Attendance.class);

		if (attendance != null) {
			return attendance;
		} else 
		{
			throw new EmployeeNotFoundException("Employee not found");
		}
	}

}
