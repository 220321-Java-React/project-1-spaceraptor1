package com.revature.controllers;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.revature.daos.ReimbDao;
import com.revature.dtos.IDDTO;
import com.revature.models.Reimb;

import io.javalin.http.Handler;

public class RequestController {
	ReimbDao rd = new ReimbDao();
	
	public Handler requestHandler = (ctx) ->{
		String body = ctx.body();
		Gson gson = new Gson();
		System.out.println(body);
		IDDTO ID = gson.fromJson(body, IDDTO.class);
		System.out.println(ID);
		int id = ID.getId();
		if(LoginController.ses == null) {
			ArrayList<Reimb> reqs = rd.getReimbsOwned(id);
			String JSONReqs = gson.toJson(reqs);
			
			ctx.result(JSONReqs);
			ctx.status(200);
		}else {
			ctx.status(400);
		}
	};
}
