package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Attendance;
import com.example.demo.model.LeaveDefaults;
import com.example.demo.model.LeaveTypes;
import com.example.demo.repository.AttendanceRepository;


@Service
public class ApplyLeaveService {
	
	@Autowired
	private MongoTemplate mongotemplate;
	
	@Autowired
	private AttendanceRepository attendancerepository;
	
	
	public String applyleave(String Id,String reason, LeaveTypes applyleav, LeaveDefaults lev) throws EmployeeNotFoundException  {
		
		String result="";
		Attendance attendance = attendancerepository.findById(Id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + Id));
		
		applyleav.setFirstName(attendance.getFirstName());
		applyleav.setPaidLeave(lev.getPaidLeave());
		applyleav.setMedicalLeave(lev.getMedicalLeave());
		applyleav.setPrivilegeLeave(lev.getPrivilegeLeave());
		applyleav.setLossOfPay(lev.getLossOfPay());
		if(reason.equalsIgnoreCase("PaidLeave")) {
			int n = lev.getPaidLeave();
			if(n>0) {
			 applyleav.setPaidLeave(n-1);
			 result = "Paid Leave Successfully Granted !";
			}
		}
		else if(reason.equals("ML")) {
			int n = lev.getMedicalLeave();
			if(n>0) {
			 applyleav.setMedicalLeave(n-1);
			 result = "Medical Leave Successfully Granted !";
			}
		}
		else if(reason.equals("PL")) {
			int n = lev.getPrivilegeLeave();
			if(n>0) {
			 applyleav.setPrivilegeLeave(n-1);
			 result = "Privilege Leave Successfully Granted !";
			}
		}
		else if(reason.equals("LOP")) {
			int n = lev.getLossOfPay();
			if(n>0) {
			 applyleav.setLossOfPay(n-1);
			 result = "LOP Leave Successfully Granted !";
			}
		}
		applyleav.setReason("Last leave applied reason is "+reason);
		mongotemplate.save(applyleav);
		return result;
	}
	
	public String applyleave(String Id,String reason, LeaveTypes applyleav) throws EmployeeNotFoundException  {
			
			String result="";
			Attendance attendance = attendancerepository.findById(Id)
					.orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + Id));
			applyleav.setFirstName(attendance.getFirstName());
			if(reason.equalsIgnoreCase("PaidLeave")) {
				
				if(applyleav.getPaidLeave() > 0) {
				 applyleav.setPaidLeave(applyleav.getPaidLeave()-1);
				 result = "Paid Leave Successfully Granted !";
				}else
				{
					result = "You have Exhaused Paid Leave's\n Paid Leave can't be applied !";
				}
				mongotemplate.save(applyleav);
			}
			else if(reason.equals("ML")) {
				
				if(applyleav.getMedicalLeave() > 0) {
				 applyleav.setMedicalLeave(applyleav.getMedicalLeave()-1);
				 result = "ML Leave Successfully Granted !";
				}else
				{
					result = "You have Exhaused ML leave's \n ML can't be applied !";
				}
				mongotemplate.save(applyleav);
			}
			else if(reason.equals("PL")) {
				
				if(applyleav.getPrivilegeLeave() > 0) {
				 applyleav.setPrivilegeLeave(applyleav.getPrivilegeLeave()-1);
				 result = "PL Leave Successfully Granted !";
				}else
				{
					result = "You have Exhaused PL leave's \n PL can't be applied !";
				}
				mongotemplate.save(applyleav);
			}
			else if(reason.equals("LOP")) {
				
				if(applyleav.getLossOfPay() > 0) {
				 applyleav.setLossOfPay(applyleav.getLossOfPay()-1);
				 result = "LOP Leave Successfully Granted !";
				}else
				{
					result = "You have Exhaused LOP leave's \n LOP can't be applied !";
				}
				mongotemplate.save(applyleav);
			}
			applyleav.setReason("Last leave applied reason is "+reason);
			return result;
		}
}
