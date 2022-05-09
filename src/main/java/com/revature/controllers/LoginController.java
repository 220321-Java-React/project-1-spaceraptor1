package com.revature.controllers;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.Launcher;
import com.revature.dtos.LoginDTO;
import com.revature.services.LoginService;
import io.javalin.http.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginController {
	Logger log = LogManager.getLogger(LoginController.class);
	LoginService ls = new LoginService();
	static HttpSession ses;
	public Handler loginHandler = (ctx) ->{
		
		String body = ctx.body();
		Gson gson = new Gson();
		//System.out.println(body);
		LoginDTO LDTO = gson.fromJson(body, LoginDTO.class);
		log.info("Login request: "+body);
		if(ls.login(LDTO.getUsername(), LDTO.getPassword()) != null) {
			
			ses = ctx.req.getSession(true);
			ctx.status(202); //202 stands for "accepted"
			
			String employeeJSON = gson.toJson(ls.login(LDTO.getUsername(), LDTO.getPassword()));
			log.info("Login succesful returning: "+employeeJSON);
			//send back our Employee JSON object
			ctx.result(employeeJSON);
			
		} else {
			ctx.status(401); //401 stands for "unauthorized"
			log.warn("Login failed");
		}
	};
}
