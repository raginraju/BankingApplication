package com.ncs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ncs.model.Model;

/**
 * Servlet implementation class ForgotPassword
 */
public class ForgotPassword extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//get inputs from forms
		String usr = request.getParameter("usr");
		String email = request.getParameter("email");

		Model m = new Model();
		m.setUsr(usr);

		ArrayList<String> userList = m.getList("username"); // returns list of registered usernames
		Iterator<String> iter = userList.iterator();
		boolean userExists=false;

		// to check whether given username is a registered username;
		while (iter.hasNext()) {
			if (iter.next().equals(usr)) {
				userExists=true;
				break;
			}
		}
		
		//System.out.println("email ID " + m.usernameMatchEmail());
		//this function provides registered user's email id
		
		//check keyed in email corresponds to the user; and user exists
		if(email.equals(m.usernameMatchEmail()) && userExists) {
			try {
				m.setUsr(usr);
				
				// Generating new Random password
				Random r = new Random(System.currentTimeMillis());
			    int newPassword = r.nextInt(10000);// generates a number between [0..9999].
				System.out.println(newPassword);
				
				//Send email to customer // set to my email: ("raginraju1@gmail.com") for test purposes 
				m.sendEmail("raginraju1@gmail.com", newPassword);
				
				//updating new password in the database
				String strNewPass = Integer.toString(newPassword);// converting numeric password to string
				int success = m.changePassword(m.encrypt(strNewPass)); // encrypting and updating password in database
				if (success == 1) {
					response.sendRedirect("/BankingApplication/ForgotPassword_updateSuccess.html");
				} else {
					response.sendRedirect("/BankingApplication/ForgotPassword_UpdateFailure.html");
				}
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		else {//user donot exist
			response.sendRedirect("/BankingApplication/ForgotPassword_UpdateFailureUsrNotFound.html");
		}
		
		
		


	}

}
