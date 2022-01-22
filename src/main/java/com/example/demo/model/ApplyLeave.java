package com.example.demo.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="AppliedLeave")
public class ApplyLeave {
	
	private String id;
	private String firstName;
	private String reason;
	
	public ApplyLeave(String id, String firstName, String reason) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.reason = reason;
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

}
