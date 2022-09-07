package com.ncs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;
import com.ncs.model.Transaction;

/**
 * Servlet implementation class TransactionList
 */
public class TransactionList extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get values from the form
		String from_date = request.getParameter("from");
		String to_date = request.getParameter("to");
		String type = request.getParameter("type");

		Model m = new Model();
		HttpSession session = request.getSession();
		m.setUsr((String) session.getAttribute("susr"));
		
		//get transaction list based on the from, to, type parameters
		ArrayList<Transaction> transactionList = m.transactionlist(from_date, to_date , type);
		
		//send data to the transactions view page
		request.setAttribute("transactionList", transactionList);
		request.getRequestDispatcher("Transactions.jsp").forward(request, response);
		
		
		//PrintWriter pw = response.getWriter();
//		for(Transaction t: transactionList) {
//			pw.println(t);
//		}
	}

}
