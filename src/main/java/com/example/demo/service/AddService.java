package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.model.Attendance;
import com.example.demo.model.DatabaseSequence;
import com.example.demo.model.WorkingHours;

@Service
public class AddService {
	
	@Autowired
	private SequenceGeneratorService service;

	@Autowired
	private MongoTemplate mongotemplate;
	
	
	public String addEmployee(Attendance attendance, String prefix) throws NumberFormatException {
		
			if(prefix.equals("AS")){
			attendance.setId(prefix+ service.getCount(DatabaseSequence.getSequenceNameAS()));
			attendance.setManager("Manager_AS");
			}
			else if(prefix.equals("BS")) {
				attendance.setId(prefix+ service.getCount(DatabaseSequence.getSequenceNameBS()));
				attendance.setManager("Manager_BS");
			}
			else if(prefix.equals("CS")){
				attendance.setId(prefix+ service.getCount(DatabaseSequence.getSequenceNameCS()));
				attendance.setManager("Manager_CS");
			}
			attendance.setStatus("Active");
			attendance.setJoiningDate(LocalDate.now());
			int n = attendance.getExperience();
			
	    	if(n >= 6) {
	    		attendance.setRole("Manager");
	    	}
	    	else if(n >= 3 && n <= 5) {
	    		attendance.setRole("Senior Developer");
	    	}
	    	else if(n <= 2 ) {
	    		attendance.setRole("Developer");
	    	}
			
			mongotemplate.save(attendance);
		    return "Employee Added";
	}
	
	public String addWorkHrs(WorkingHours workhrs) {
		workhrs.setCheckIn(LocalDateTime.now());
		mongotemplate.save(workhrs);
	    return "Employee Added";
	}
	
	public int getTeamCount(String Id) {
		int n = 0;
		if(Id.equals("AS")){
			n = (service.getCount(DatabaseSequence.getSequenceNameAS())) - 1;
		}
		else if(Id.equals("BS")) {
			n = (service.getCount(DatabaseSequence.getSequenceNameBS())) - 1;
		}
		else if(Id.equals("CS")) {
			n = (service.getCount(DatabaseSequence.getSequenceNameCS())) - 1;
		}
		return n;
	}

}
