package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Attendance;

@Repository
public interface AttendanceRepository extends MongoRepository<Attendance, String>{

}
