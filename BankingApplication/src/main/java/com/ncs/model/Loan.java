package com.ncs.model;

public class Loan {
	
	
	
	String usr;
	int loan_amount;
	String type;
	String status;
	int loanID;
	
	public String getUsr() {
		return usr;
	}
	public void setUsr(String usr) {
		this.usr = usr;
	}
	public int getLoan_amount() {
		return loan_amount;
	}
	public void setLoan_amount(int loan_amount) {
		this.loan_amount = loan_amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	

	public int getLoanID() {
		return loanID;
	}
	public void setLoanID(int loanID) {
		this.loanID = loanID;
	}
	@Override
	public String toString() {
		return "Loan [usr=" + usr + ", loan_amount=" + loan_amount + ", type=" + type + ", status=" + status
				+ ", loanID=" + loanID + "]";
	}
	
	public Loan(String usr, int loan_amount, String type, String status, int loanID) {
		super();
		this.usr = usr;
		this.loan_amount = loan_amount;
		this.type = type;
		this.status = status;
		this.loanID = loanID;
	}
	
}
