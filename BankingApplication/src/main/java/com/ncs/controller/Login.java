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
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//starting a new session
		HttpSession session = request.getSession(true);
		
		//get all the inputs from the form
		String usr = request.getParameter("usr");
		String pwd = request.getParameter("pwd");
		
		Model m = new Model();
		
		//setters
		m.setUsr(usr);
		String encrypted_pwd = m.encrypt(pwd); //password encryption
		m.setPwd(encrypted_pwd);//Storing encrypted password 
		
		//login
		int loginStatus = m.login();

		if (loginStatus == 0) {//incorrect password
			System.out.println("Incorrect Password");
			response.sendRedirect("/BankingApplication/InvalidPassword.html");
			
		} else if(loginStatus == -1) {// incorrect username
			System.out.println("Incorrect Username");
			response.sendRedirect("/BankingApplication/InvalidUsername.html");
			
		}
		else {//login success
			
			//load data to session
			session.setAttribute("sname", m.getName());
			session.setAttribute("susr", m.getUsr());
			session.setAttribute("spwd", m.getPwd());
			session.setAttribute("sphone", m.getPhone());
			session.setAttribute("semail", m.getEmail());
			session.setAttribute("sacc_bal", m.getAcc_bal());
			
			System.out.println("login success");
			response.sendRedirect("/BankingApplication/Home.jsp");		
			
		}
		
	}

}
