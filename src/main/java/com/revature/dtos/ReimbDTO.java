package com.revature.dtos;

import java.util.Arrays;

import com.revature.daos.ReimbDao;
import com.revature.daos.UserDao;
import com.revature.models.Reimb;
import com.revature.models.User;

public class ReimbDTO {
	private int id;
	private double amount;
	private String type;
	private String status;
	private String resolver;
	private String email;
	
	private final String[] stats = {"", "Pending", "Approved", "Denied"};
	private final String[] types = {"","Lodging","Travel","Food","Other"};
	private UserDao ud = new UserDao();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResolver() {
		return resolver;
	}
	public void setResolver(String resolver) {
		this.resolver = resolver;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public ReimbDTO(Reimb req) {
		id = req.getId();
		amount = req.getAmount();
		type = types[req.getType()];
		status = stats[req.getStatus()];
		//if(req.getResolver() != null) {
		if(ud.getUserById(req.getResolver()) != null) {
			User u = ud.getUserById(req.getResolver());
			resolver = u.getFirstname()+" "+u.getLastname();
			email = u.getEmail();
		}else {
			resolver = "";
			email = "";
		}
		
	}
	
	public ReimbDTO() {
		super();
	}
	public ReimbDTO reimbConstructor(Reimb req) {
		id = req.getId();
		amount = req.getAmount();
		type = types[req.getType()];
		status = stats[req.getStatus()];
		//if(req.getResolver() != null) {
		if(ud.getUserById(req.getAuthor()) != null) {
			User u = ud.getUserById(req.getAuthor());
			resolver = u.getFirstname()+" "+u.getLastname();
			email = u.getEmail();
		}else {
			resolver = "";
			email = "";
		}
		return this;
		
	}
	
	@Override
	public String toString() {
		return "ReimbDTO [id=" + id + ", amount=" + amount + ", type=" + type + ", status=" + status + ", resolver="
				+ resolver + ", email=" + email + "]";
	}
}
