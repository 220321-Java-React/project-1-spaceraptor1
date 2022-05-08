package com.revature.controllers;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.revature.daos.ReimbDao;
import com.revature.dtos.CreateDTO;
import com.revature.dtos.IDDTO;
import com.revature.dtos.ReimbDTO;
import com.revature.dtos.ResolveDTO;
import com.revature.models.Reimb;

import io.javalin.http.Handler;

public class RequestController {
	ReimbDao rd = new ReimbDao();
	
	public Handler requestHandler = (ctx) ->{
		String body = ctx.body();
		Gson gson = new Gson();
		//System.out.println(body);
		//IDDTO ID = gson.fromJson(body, IDDTO.class);
		int id = gson.fromJson(body, int.class);
		System.out.println(id);
		//int id = ID.getId();
		if(LoginController.ses != null) {
			ArrayList<Reimb> reqs = rd.getReimbsOwned(id);
			ArrayList<ReimbDTO> Reqs = new ArrayList<>();
			
			reqs.forEach(r -> {
				Reqs.add(new ReimbDTO(r));
				System.out.println(new ReimbDTO(r));
			});
			
			String JSONReqs = gson.toJson(Reqs);
			
			ctx.result(JSONReqs);
			ctx.status(200);
		}else {
			ctx.status(400);
		}
	};
	
	public Handler createHandler = (ctx) ->{
		String body = ctx.body();
		Gson gson = new Gson();
		CreateDTO cd = gson.fromJson(body, CreateDTO.class);
		System.out.println(cd);
		Reimb r = new Reimb(cd.getAmount(),cd.getAuthor(),cd.getType());
		
		if(LoginController.ses != null){
			if(rd.createReimb(r)) {
				ctx.status(201);
			}else {ctx.status(400);}
		}else {ctx.status(401);}
	};

	public Handler pendingHanler = (ctx) ->{
		String body = ctx.body();
		Gson gson = new Gson();
		int id = gson.fromJson(body, int.class);
		System.out.println(id);
		
		if(LoginController.ses != null) {
			ArrayList<Reimb> reqs = rd.getReimbsNotOwned(id);
			ArrayList<ReimbDTO> Reqs = new ArrayList<>();
			
			reqs.forEach(r -> {
				Reqs.add((new ReimbDTO()).reimbConstructor(r));
				System.out.println(new ReimbDTO(r));
			});
			
			String JSONReqs = gson.toJson(Reqs);
			
			ctx.result(JSONReqs);
			ctx.status(200);
		}else {
			ctx.status(400);
		}
		
	};

	public Handler resolveHandler = (ctx) ->{
		String body = ctx.body();
		Gson gson = new Gson();
		ResolveDTO r = gson.fromJson(body, ResolveDTO.class);
		System.out.println(r);
		if(LoginController.ses != null) {
			if(rd.updateReimbStatus(r)) {
				ctx.status(200);
			}else {ctx.status(400);}
		}else {ctx.status(401);}
	};
}
