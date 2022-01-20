package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.LeaveTypes;

@Repository
public interface LeaveRepository extends MongoRepository<LeaveTypes, String> {

}
