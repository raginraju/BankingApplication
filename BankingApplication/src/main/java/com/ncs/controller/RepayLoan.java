package com.ncs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;

/**
 * Servlet implementation class RepayLoan
 */
public class RepayLoan extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//getting username from the session
				HttpSession session = request.getSession();
				String usr = (String) session.getAttribute("susr");
				
				//System.out.println("at repay page");
				int repayAmt = Integer.parseInt(request.getParameter("repayAmt"));
				int loanID = Integer.parseInt(request.getParameter("loanID"));
				System.out.println(repayAmt);
				System.out.println(loanID);
				
				boolean isNotLoan = false; 
				String payee = "LoanID#"+loanID;
				
				Model m = new Model();
				m.setUsr(usr);
				
				//1. updating loan table by deducting repay amount from loan amount
				int rows= m.updateLoanAmount(loanID, repayAmt);

				//2. updating transaction table
				rows+= m.updateTransactionTable(repayAmt,payee,isNotLoan);

				//3. updating account balance  //payee not required since no recipient
				rows+= m.transfer(repayAmt,payee,isNotLoan); 

				if(rows==3) {
					System.out.println("success");
					response.sendRedirect("/BankingApplication/CheckBalance1");
				}
				else {
					System.out.println("failed");
				}
				
	}
}
