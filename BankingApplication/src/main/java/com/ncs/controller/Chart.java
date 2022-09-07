package com.ncs.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Customer;
import com.ncs.model.Loan;
import com.ncs.model.Model;
import com.ncs.model.Transaction;

/**
 * Servlet implementation class Chart
 */
public class Chart extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model m = new Model();
		HttpSession session = request.getSession();
		//m.setUsr((String) session.getAttribute("susr"));
		
		//1. loan List
		ArrayList<Loan> loanlist = m.loanList();
		
		//2. customer list
		ArrayList<Customer> customerlist = m.customerList();
		
		//3. allDebitedTransactionsList
		ArrayList<Transaction> allDebitedTransactionsList = m.allDebitedTransactions();
		
			
		
		//send data to charts jsp page
		request.setAttribute("loanlist", loanlist);
		request.setAttribute("customerlist", customerlist);
		request.setAttribute("allDebitedTransactionsList", allDebitedTransactionsList);
		request.getRequestDispatcher("Charts.jsp").forward(request, response);
	}
}
