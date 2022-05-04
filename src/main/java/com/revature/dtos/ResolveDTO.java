package com.revature.dtos;

public class ResolveDTO {
	private int resolver, status, id;

	public int getResolver() {
		return resolver;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	/*
	 * public int getAuthor() { return author; }
	 * 
	 * public void setAuthor(int author) { this.author = author; }
	 */

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ResolveDTO [resolver=" + resolver + ", status=" + status + ", id=" + id + "]";
	}

	
	
}
