package com.example.demo.model;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="WorkHours")
public class WorkingHours {
	
	private String id;
	private String firstName;
	private LocalDateTime checkIn = LocalDateTime.now();
	private LocalDateTime checkOut;
	
	private List<Time> inDetailedTimings;

	public WorkingHours() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WorkingHours(String id, String firstName, LocalDateTime checkIn, LocalDateTime checkOut, List<Time> inDetailedTimings) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.inDetailedTimings = inDetailedTimings;
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

	public LocalDateTime getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDateTime checkIn) {
		this.checkIn = checkIn;
	}

	public LocalDateTime getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDateTime checkOut) {
		this.checkOut = checkOut;
	}

	public List<Time> getInDetailedTimings() {
		return inDetailedTimings;
	}

	public void setInDetailedTimings(List<Time> inDetailedTimings) {
		this.inDetailedTimings = inDetailedTimings;
	}

	@Override
	public String toString() {
		return "WorkingHours [id=" + id + ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", inDetailedTimings="
				+ inDetailedTimings + "]";
	}
	
	

}
