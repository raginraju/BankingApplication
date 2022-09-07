package com.ncs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;

/**
 * Servlet implementation class LoanApplication
 */
public class LoanApplication extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get user input
		int amount = Integer.parseInt(request.getParameter("amount"));
		String type = request.getParameter("type");
		
		//get current session
		HttpSession session = request.getSession();
		String usr = (String) session.getAttribute("susr");
		
		//setters
		Model m = new Model();
		m.setUsr(usr);
		m.setLoan_amount(amount);
		m.setType(type);

		int success=0;
		int isApproved=0;
		
		// retrieve account balance from database
		int balance = m.getAccBalanceofApplicant(usr);
		System.out.println("retrieved bal" + balance);
		
		//check whether user eligible for loan
		//Eligibility==> requested loan amount+ total approved loan < balance*10
		if((balance*10)>(amount+m.pendingLoanAmount())) {//Eligible
			success= m.UpdateLoanStatus(true);//loan approved
			isApproved=1; //loan approved
		}
		else {//Not eligible 
			success = m.UpdateLoanStatus(false); //loan rejected
		}
		
		//query executed successfully , loan approved
		if (success == 1 && isApproved==1) {		
			response.sendRedirect("/BankingApplication/LoanApplication_Success.jsp");
		}
		//query executed successfully , loan rejected
		else if (success == 1 && isApproved==0) { 
			response.sendRedirect("/BankingApplication/LoanApplication_Rejected.jsp");
			
		}
		//unable to execute query
		else { 
			response.sendRedirect("/BankingApplication/LoanApplication_Failure.jsp");
			
		}
		
	}

}
