package com.ncs.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Loan;
import com.ncs.model.Model;

/**
 * Servlet implementation class TransferMoney
 */

public class TransferMoney extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get data from the user input
		int SI = Integer.parseInt(request.getParameter("payee"));
		int Transfer_amount = Integer.parseInt(request.getParameter("amount"));
		String payee="";//to cross check with the payee list and serial number
		
		Model m = new Model();
		//get current session
		HttpSession session = request.getSession();
		m.setUsr((String) session.getAttribute("susr"));
		
		//System.out.println("usr "+ (String) session.getAttribute("susr"));
		//get current userlist to compare with the serial number
		ArrayList<String> usernameList = m.getList("username");
		
		//To the remove the name of the logged in user as payee
		usernameList.remove(String.valueOf((String) session.getAttribute("susr")));
		
		//get the username from the corresponding serial number
		for (int i = 0; i < usernameList.size(); i++) {
			//System.out.print(loanlist.get(i) + " ");  
			if (i+1==SI) {
				payee = usernameList.get(i);
			}
		}

		
		int Transfer_success=0;
		boolean isNotLoan=true;
		
		//get account balance from db
		int curr_bal = m.getAccBalanceofApplicant((String) session.getAttribute("susr"));
		//System.out.println("curr_bal "+curr_bal);
		
		 
		//check transfer amount< current balance
		if(Transfer_amount< curr_bal) {
			Transfer_success = m.transfer(Transfer_amount,payee,isNotLoan);
			
		}
		
		if(Transfer_success==2) {//==2 coz we are executing 2 queries at the same time
			System.out.println("Transfer success");
			session.setAttribute("sacc_bal",(curr_bal-Transfer_amount));
			int transactionTableUpdate= m.updateTransactionTable(Transfer_amount,payee,isNotLoan);
			if(transactionTableUpdate==2) {//==2 coz we are executing 2 queries at the same time
				System.out.println("Transaction table udpated");
			}
			response.sendRedirect("/BankingApplication/TransferMoneySuccess.jsp");
		}
		else {
			System.out.println("Transfer failed");
			response.sendRedirect("/BankingApplication/TransferMoneyFailure.jsp");
		}
		
		
	}

}
