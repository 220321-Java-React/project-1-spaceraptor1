package com.revature.services;

import com.revature.daos.UserDao;
import com.revature.models.User;

public class LoginService {
	public User login(String username, String password) {
		UserDao ud = new UserDao();
		if(ud.getUserByUsername(username) != null) {
			
			if(ud.getUserByUsername(username).getPassword().equals(password)) {
				return ud.getUserByUsername(username);
			}else {return null;}
			
		}else if(ud.getUserByEmail(username) != null) {
			if(ud.getUserByEmail(username).getPassword().equals(password)) {
				return ud.getUserByUsername(username);
			}else {return null;}
		}else {
			return null;
		}
	}
}
