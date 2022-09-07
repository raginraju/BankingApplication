package com.ncs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;

/**
 * Servlet implementation class AdminLogin
 */
public class AdminLogin extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Creating new session 
		HttpSession session = request.getSession(true);
		
		//get inputs from the form
		String usr = request.getParameter("usr");
		String pwd = request.getParameter("pwd");
		
		Model m = new Model();
		m.setUsr(usr);
		m.setPwd(pwd);		
		
		//login
		int loginStatus = m.adminLogin();

		if (loginStatus == 0) {
			System.out.println("Incorrect Password");
			response.sendRedirect("/BankingApplication/AdminLogin_InvalidPassword.html");
			
		} else if(loginStatus == -1) {
			System.out.println("Incorrect Username");
			response.sendRedirect("/BankingApplication/AdminLogin_InvalidUsername.html");
			
		}
		else {
			session.setAttribute("susr", m.getUsr());
			System.out.println("login success");
			response.sendRedirect("/BankingApplication/Admin_Home.jsp");
			
			
		}
	}

}
