package com.ncs.model;

public class Customer {
	
	String name;
	String usr;
	int phone;
	String email;
	int acc_bal;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsr() {
		return usr;
	}
	public void setUsr(String usr) {
		this.usr = usr;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAcc_bal() {
		return acc_bal;
	}
	public void setAcc_bal(int acc_bal) {
		this.acc_bal = acc_bal;
	}
	
	public Customer(String name, String usr, int phone, String email, int acc_bal) {
		super();
		this.name = name;
		this.usr = usr;
		this.phone = phone;
		this.email = email;
		this.acc_bal = acc_bal;
	}
	
	@Override
	public String toString() {
		return "Customer [name=" + name + ", usr=" + usr + ", phone=" + phone + ", email=" + email + ", acc_bal="
				+ acc_bal + "]";
	}
}
