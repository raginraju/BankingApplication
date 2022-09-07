package com.ncs.model;

public class Transaction {
	String date;
	String usr;
	int amount;
	String type;
	String from_to;
	
	 Transaction(String date,String usr, int amount, String type, String from_to) {
		this.date = date;
		this.usr = usr;
		this.amount = amount;
		this.type = type;
		this.from_to= from_to;
	}

	@Override
	public String toString() {
		return "Transaction [date=" + date +", usr=" + usr + ", amount=" + amount + ", type=" + type +  ", from_to=" + from_to +"]";
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public String getAmount() {
		return String.valueOf(amount);
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFrom_to() {
		return from_to;
	}

	public void setFrom_to(String from_to) {
		this.from_to = from_to;
	}
	
	
}
