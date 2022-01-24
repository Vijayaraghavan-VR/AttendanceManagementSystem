package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.ApplyLeave;
import com.example.demo.model.LeaveDefaults;
import com.example.demo.model.LeaveTypes;
import com.example.demo.repository.ApplyLeaveRepository;
import com.example.demo.repository.LeaveRepository;


@Service
public class ApplyLeaveService {
	
	@Autowired
	private MongoTemplate mongotemplate;
	
	@Autowired
	private LeaveRepository levrepo;
	
	@Autowired
	private ApplyLeaveRepository applylevrepo;
	
	
	
	public String approveLeave(String Id, LeaveDefaults lev) throws EmployeeNotFoundException {
		String result="";
		LeaveTypes reqEntity = mongotemplate.findById(Id, LeaveTypes.class);
		if(reqEntity != null) {
			ApplyLeave respon = applylevrepo.findById(Id)
					.orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + Id));
			String reason = respon.getReason();
			LeaveTypes applyleav = levrepo.findById(Id)
					.orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + Id));
			applyleav.setFirstName(respon.getFirstName());
			if(reason.equalsIgnoreCase("PaidLeave")) {
				
				if(applyleav.getPaidLeave() > 0) {
				 applyleav.setPaidLeave(applyleav.getPaidLeave()-1);
				 result = "Paid Leave Successfully Granted for "+Id+".";
				}else
				{
					result = Id+" have Exhaused Paid Leave's\n Paid Leave rejected !";
				}
				mongotemplate.save(applyleav);
			}
			else if(reason.equals("ML")) {
				
				if(applyleav.getMedicalLeave() > 0) {
				 applyleav.setMedicalLeave(applyleav.getMedicalLeave()-1);
				 result = "ML Leave Successfully Granted for "+Id+".";
				}else
				{
					result = Id+" have Exhaused ML leave's \n ML rejected !";
				}
				mongotemplate.save(applyleav);
			}
			else if(reason.equals("PL")) {
				
				if(applyleav.getPrivilegeLeave() > 0) {
				 applyleav.setPrivilegeLeave(applyleav.getPrivilegeLeave()-1);
				 result = "PL Leave Successfully Granted for "+Id+".";
				}else
				{
					result = Id+" have Exhaused PL leave's \n PL rejected !";
				}
				mongotemplate.save(applyleav);
			}
			else if(reason.equals("LOP")) {
				
				if(applyleav.getLossOfPay() > 0) {
				 applyleav.setLossOfPay(applyleav.getLossOfPay()-1);
				 result = "LOP Leave Successfully Granted for "+Id+".";
				}else
				{
					result = Id+" have Exhaused LOP leave's \n LOP rejected !";
				}
				mongotemplate.save(applyleav);
				
			}
			applyleav.setReason("Last leave applied reason is "+reason);
			Query qq = new Query();
			qq.addCriteria(Criteria.where("_id").is(Id));
			mongotemplate.findAndRemove(qq, ApplyLeave.class);
			mongotemplate.save(applyleav);
			
		}
		else if(reqEntity == null){
		ApplyLeave response = applylevrepo.findById(Id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + Id));
		
		String reason = response.getReason();
		LeaveTypes applyleav = new LeaveTypes();

		applyleav.setId(response.getId());
		applyleav.setFirstName(response.getFirstName());
		applyleav.setPaidLeave(lev.getPaidLeave());
		applyleav.setMedicalLeave(lev.getMedicalLeave());
		applyleav.setPrivilegeLeave(lev.getPrivilegeLeave());
		applyleav.setLossOfPay(lev.getLossOfPay());
		
		if(reason.equalsIgnoreCase("PaidLeave")) {
			int n = lev.getPaidLeave();
			if(n>0) {
			 applyleav.setPaidLeave(n-1);
			 result = "Paid Leave Successfully Granted for "+Id+".";
			}
		}
		else if(reason.equals("ML")) {
			int n = lev.getMedicalLeave();
			if(n>0) {
			 applyleav.setMedicalLeave(n-1);
			 result = "Medical Leave Successfully Granted for "+Id+".";
			}
		}
		else if(reason.equals("PL")) {
			int n = lev.getPrivilegeLeave();
			if(n>0) {
			 applyleav.setPrivilegeLeave(n-1);
			 result = "Privilege Leave Successfully Granted for "+Id+".";
			}
		}
		else if(reason.equals("LOP")) {
			int n = lev.getLossOfPay();
			if(n>0) {
			 applyleav.setLossOfPay(n-1);
			 result = "LOP Leave Successfully Granted for "+Id+".";
			}
		}
		applyleav.setReason("Last leave applied reason is "+reason);
		mongotemplate.save(applyleav);
		Query qq = new Query();
		qq.addCriteria(Criteria.where("_id").is(Id));
		mongotemplate.findAndRemove(qq, ApplyLeave.class);
		}
		return result;
	}
}
