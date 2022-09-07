package com.ncs.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Customer;
import com.ncs.model.Model;


/**
 * Servlet implementation class AdminViewRegiseredCustomers
 */
public class AdminViewRegiseredCustomers extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model m = new Model();
		
		//getting current session
		HttpSession session = request.getSession();
		m.setUsr((String) session.getAttribute("susr"));
		
		//getting list of customers and their details except password
		ArrayList<Customer> customerlist = m.customerList();
		
		//redirect to the view registered customer page
		request.setAttribute("customerlist", customerlist);
		request.getRequestDispatcher("AdminView_RegisteredCustomers.jsp").forward(request, response);
	}

}
