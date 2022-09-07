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
 * Servlet implementation class CheckBalance1
 */
public class CheckBalance1 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get existing session
		HttpSession session = request.getSession();
		
		//getting username from the session
		String usr = (String) session.getAttribute("susr");
				
		Model m = new Model();
		
		//Retrieving account balance
		int accBal = m.getAccBalanceofApplicant(usr);
		//System.out.println(accBal);
		
		//Retrieving list of approved loans
		ArrayList<Loan> loanlist = m.loanList();// get all the loans
		ArrayList<Loan> approvedLoanList = new ArrayList<Loan>() ;
		
		for(Loan l : loanlist ) {			  
			if( l.getUsr().equals(usr) && l.getStatus().equals("Approved") && l.getLoan_amount()>0) {
				//get approved loan of the logged in user and loan amount greater than zero
				approvedLoanList.add(l);
			}
		}
		
		//send to display page
		request.setAttribute("accBal", accBal);
		request.setAttribute("approvedLoanList", approvedLoanList);
		request.getRequestDispatcher("CheckBalance.jsp").forward(request, response);
	}

}
