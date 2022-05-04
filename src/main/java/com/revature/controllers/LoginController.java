package com.revature.controllers;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.dtos.LoginDTO;
import com.revature.services.LoginService;
import io.javalin.http.Handler;
public class LoginController {
	LoginService ls = new LoginService();
	static HttpSession ses;
	public Handler loginHandler = (ctx) ->{
		String body = ctx.body();
		Gson gson = new Gson();
		System.out.println(body);
		LoginDTO LDTO = gson.fromJson(body, LoginDTO.class);
		System.out.println(LDTO);
		if(ls.login(LDTO.getUsername(), LDTO.getPassword()) != null) {
			
			ses = ctx.req.getSession(true);
			ctx.status(202); //202 stands for "accepted"
			
			String employeeJSON = gson.toJson(ls.login(LDTO.getUsername(), LDTO.getPassword()));
			System.out.println(employeeJSON);
			//send back our Employee JSON object
			ctx.result(employeeJSON);
			
		} else {
			ctx.status(401); //401 stands for "unauthorized"
			System.out.println("heyo login failed");
		}
	};
}
