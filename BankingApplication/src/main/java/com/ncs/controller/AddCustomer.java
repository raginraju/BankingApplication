package com.ncs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;

/**
 * Servlet implementation class AddCustomer
 */
public class AddCustomer extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		boolean usr_taken = false;
		//Get all the inputs here
		String name = request.getParameter("name");
		String usr = request.getParameter("usr");
		String pwd = request.getParameter("pwd");//Register filter check pwd cpwd are matching
		String cpwd = request.getParameter("cpwd");
		int phone = Integer.parseInt(request.getParameter("phone"));
		String email = request.getParameter("email");

		Model m = new Model();
		
		//setters 
		m.setName(name);
		m.setUsr(usr);
		String encrypted_pwd = m.encrypt(pwd); //password encryption
		m.setPwd(encrypted_pwd);//Storing encrypted password inside the database
		m.setPhone(phone);
		m.setEmail(email);
		
		//Check username is taken or not
		ArrayList<String> userNameList = m.getList("username"); //returns list of usernames
		Iterator<String> iter = userNameList.iterator();
		
		 while (iter.hasNext()){
			if(iter.next().equals(usr)) {// username alr exists
				usr_taken=true;
				response.sendRedirect("/BankingApplication/RegisterFailure_UserNameExists.html");
				break;
			}
		}
		
		//registering new user when keyed in username is unique
		if(!usr_taken) {
			int rows = m.register();
			if (rows == 0) {
				response.sendRedirect("/BankingApplication/RegisterFailure.html");
			} else {
				response.sendRedirect("/BankingApplication/RegisterSuccess.jsp");
			}
		}
		

	}

}
