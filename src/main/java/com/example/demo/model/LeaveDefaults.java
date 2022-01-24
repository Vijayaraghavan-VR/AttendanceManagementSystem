package com.example.demo.model;

public class LeaveDefaults {

	private int paidLeave = 2;
	private int medicalLeave = 4;
	private int privilegeLeave = 1;
	private int lossOfPay = 2;
	private int onDuty = 3;


	public LeaveDefaults(int paidLeave, int medicalLeave, int privilegeLeave, int lossOfPay, int onDuty) {
		super();
		this.paidLeave = paidLeave;
		this.medicalLeave = medicalLeave;
		this.privilegeLeave = privilegeLeave;
		this.lossOfPay = lossOfPay;
		this.onDuty = onDuty;
	}

	public LeaveDefaults() {
		super();
		// TODO Auto-generated constructor stub
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

	public int getPrivilegeLeave() {
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

	public int getOnDuty() {
		return onDuty;
	}

	public void setOnDuty(int onDuty) {
		this.onDuty = onDuty;
	}
}
