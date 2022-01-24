package com.example.demo.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="LeaveLog")
public class LeaveTypes {
	
	private String id;
	private String firstName;
	private String reason;
	private int paidLeave;
	private int medicalLeave;
	private int privilegeLeave;
	private int lossOfPay;

	
	public LeaveTypes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveTypes(String id, String firstName, int paidLeave, int medicalLeave, int privilegeLeave, int lossOfPay, int onDuty) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.paidLeave = paidLeave;
		this.medicalLeave = medicalLeave;
		this.privilegeLeave = privilegeLeave;
		this.lossOfPay = lossOfPay;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
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

	public int getPaidLeave() {
		return paidLeave;
	}
	
	public void setPaidLeave(int paidLeave) {
		this.paidLeave = paidLeave;
	}

	public int getMedicalLeave() {
		return medicalLeave;
	}

	public void setMedicalLeave(int medicalLeave) {
		this.medicalLeave = medicalLeave;
	}

	public  int getPrivilegeLeave() {
		return privilegeLeave;
	}

	public void setPrivilegeLeave(int privilegeLeave) {
		this.privilegeLeave = privilegeLeave;
	}

	public int getLossOfPay() {
		return lossOfPay;
	}

	public void setLossOfPay(int lossOfPay) {
		this.lossOfPay = lossOfPay;
	}
}
