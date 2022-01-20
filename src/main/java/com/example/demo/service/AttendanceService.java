package com.example.demo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.demo.model.Attendance;

@Service
public class AttendanceService {

	@Autowired
	private MongoTemplate mongotemplate;
	
	public void updateemployee(Map<String, String> attendance, String Id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(Id));
		Update update = new Update();
		for (@SuppressWarnings("rawtypes") Map.Entry test : attendance.entrySet()) {
			if(test.getKey() == "role") {
				continue;
			}
			update.set((String) test.getKey(), test.getValue());
		}
		mongotemplate.findAndModify(query, update, Attendance.class);
	}
}
