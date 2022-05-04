package com.revature.models;

public class Reimb {
	private int id;
	private int amount;
	private int author;
	private int resolver;
	private int status;
	private int type;
	
	public Reimb(int id, int amount, int author, int resolver, int status, int type) {
		super();
		this.id = id;
		this.amount = amount;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}

	public Reimb(int amount, int author, int resolver, int status, int type) {
		super();
		this.amount = amount;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
