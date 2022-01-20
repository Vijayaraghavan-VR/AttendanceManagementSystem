package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.WorkingHours;

@Repository
public interface WorkingHoursRepository extends MongoRepository<WorkingHours, String> {

}
