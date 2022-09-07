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
 * Servlet implementation class AdminViewLoanApplications
 */
public class AdminViewLoanApplications extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model m = new Model();
		
		//Getting details from current session
		HttpSession session = request.getSession();
		m.setUsr((String) session.getAttribute("susr"));
		
		//getting loanlist from the db
		ArrayList<Loan> loanlist = m.loanList();
		
		//declaring arraylist for data collection
		ArrayList<Loan> approvedLoanList = new ArrayList<Loan>() ;
		ArrayList<Loan> rejectedLoanList = new ArrayList<Loan>() ;
		
		//segregating the result from loan list table based on the status
		for(Loan l : loanlist ) {
			if(l.getStatus().equals("Approved")) {
				approvedLoanList.add(l);
			}
			else {
				rejectedLoanList.add(l);
			}
		}
		
		//sending result to the view page
		request.setAttribute("approvedLoanList", approvedLoanList);
		request.setAttribute("rejectedLoanList", rejectedLoanList);
		request.getRequestDispatcher("AdminView_ViewLoans.jsp").forward(request, response);
	}

}
