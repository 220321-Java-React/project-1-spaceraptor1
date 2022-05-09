package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.controllers.LoginController;
import com.revature.controllers.RequestController;
import com.revature.utils.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.javalin.Javalin;

public class Launcher {

    public static void main(String[] args) {
    	try(Connection conn = ConnectionUtil.getConnection()){
			System.out.println("CONNECTION SUCCESSFUL :)");
		} catch (SQLException e) { //if creating this connection fails... catch the exception and print the stack trace
			System.out.println("connection failed... :(");
			e.printStackTrace();
		}
    	Logger log = LogManager.getLogger(Launcher.class);
    	log.info("hello");
    	LoginController lc = new LoginController();
    	RequestController rc = new RequestController();
    	
    	Javalin app = Javalin.create(
				config -> {
					config.enableCorsForAllOrigins(); 
				}
			).start(3005);
    	app.post("/login", lc.loginHandler);
    	app.post("/myReqs", rc.requestHandler);
    	app.put("/create", rc.createHandler);
    	app.post("/pending",rc.pendingHanler);
    	app.put("/resolve",rc.resolveHandler);
    	/*
    	  
    	  
		       _.---._    /\\
		    ./'       "--`\//
		  ./              o \
		 /./\  )______   \__ \
		./  / /\ \   | \ \  \ \
		   / /  \ \  | |\ \  \7
		    "     "    "  "        
    	  
    	  
    	  Here's an aardvark this time... not as friendly or cute as a dog, but take him anyway.
    	 
    	 */
    	
    }
}
