package com.revature.dtos;

public class CreateDTO {
	private int type;
	private double amount;
	private int author;
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "CreateDTO [type=" + type + ", amount=" + amount + "]";
	}
	
}
