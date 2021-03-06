package com.example.demo.model;


import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "Attendance")
public class Attendance {
	

	
	@NotBlank
    @Size(max=100)
    @Indexed(unique=true)
	private String id;
	
	private String firstName;
	private String lastName;
	
	@NotBlank
    @Size(max=100)
    @Indexed(unique=true)
	private String emailId;
	
	private long phoneNumber;
	private LocalDate dob;
	private int experience;

	public String role;
	private String status = "Active";
	private String manager;
	private LocalDate joiningDate = LocalDate.now();
	private LocalDate terminatedDate;
	private String team;

	public Attendance() {
		super();
	}
	
	public Attendance( String firstName, String lastName, String emailId, long phoneNumber, LocalDate dob,
			int experience, String team) {
		

		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
		this.experience = experience;
		this.team = team;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public LocalDate getTerminatedDate() {
		return terminatedDate;
	}

	public void setTerminatedDate(LocalDate terminatedDate) {
		this.terminatedDate = terminatedDate;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}
}
