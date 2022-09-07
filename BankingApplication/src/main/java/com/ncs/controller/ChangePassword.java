package com.ncs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;

/**
 * Servlet implementation class ChangePassword
 */
public class ChangePassword extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//getting all inputs from form
		String old_pwd = request.getParameter("old_pwd");
		String new_pwd = request.getParameter("new_pwd"); //new pwd and confirm pwd filtered
		String confirm_pwd = request.getParameter("confirm_pwd");

		//getting the existing session
		HttpSession session = request.getSession();
			
		Model m = new Model();
		//password encryption
		String encrypted_old_pwd= m.encrypt(old_pwd);
		String encrypted_new_pwd= m.encrypt(new_pwd);
		
		//****check session password vs keyed old password****
			if(((String) session.getAttribute("spwd")).equals(encrypted_old_pwd)) { //session password and keyed in old password  match
				m.setUsr((String) session.getAttribute("susr"));
				int rows = m.changePassword(encrypted_new_pwd);

				if (rows == 0) {//database update failed
					response.sendRedirect("/BankingApplication/ChangePassword_Failed.html");
				} else {//database update success
					session.setAttribute("spwd",m.encrypt(new_pwd) ); // session pwd updated when success
					response.sendRedirect("/BankingApplication/ChangePasswordSuccess_NewPwdLogin.html");
				}
			}
			else {//session password and keyed in old password doesnt match
				response.sendRedirect("/BankingApplication/ChangePassword_InvalidOldPassword.html");
			}
//		}
			

	}

}
