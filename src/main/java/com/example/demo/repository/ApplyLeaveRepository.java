package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ApplyLeave;

@Repository
public interface ApplyLeaveRepository extends MongoRepository<ApplyLeave, String>{

}