package com.example.demo.service;

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
	
	public String addEmployee(Attendance attendance, String prefix) {
		
			if(prefix.equals("AS")){
			attendance.setId(prefix+ service.getCount(DatabaseSequence.getSequenceNameAS()));
			}
			else if(prefix.equals("BS")) {
				attendance.setId(prefix+ service.getCount(DatabaseSequence.getSequenceNameBS()));
			}
			else if(prefix.equals("CS")){
				attendance.setId(prefix+ service.getCount(DatabaseSequence.getSequenceNameCS()));
			}
			mongotemplate.save(attendance);
		    return "Employee Added";
	}
	
	public String addWorkHrs(WorkingHours workhrs) {

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
