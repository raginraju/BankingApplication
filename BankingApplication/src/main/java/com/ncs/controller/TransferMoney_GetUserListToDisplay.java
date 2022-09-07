package com.ncs.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;

/**
 * Servlet implementation class TransferMoney_GetUserListToDisplay
 */
public class TransferMoney_GetUserListToDisplay extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model m = new Model();
		
		//get current session
		HttpSession session = request.getSession();
		
		//setter
		m.setUsr((String) session.getAttribute("susr"));
		
		//get list of all the username to display as list of payees
		ArrayList<String> usernameList = m.getList("username");
		
		//To the remove the name of the logged in user as payee
		usernameList.remove(String.valueOf((String) session.getAttribute("susr")));
		
		//send data to transfer money page
		request.setAttribute("usernameList", usernameList);
		request.getRequestDispatcher("TransferMoney.jsp").forward(request, response);
	}


}
