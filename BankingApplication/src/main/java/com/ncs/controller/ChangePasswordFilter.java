package com.ncs.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class ChangePasswordFilter
 */
public class ChangePasswordFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ChangePasswordFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String new_pwd  = request.getParameter("new_pwd");
		String confirm_pwd = request.getParameter("confirm_pwd");
		
		if(new_pwd.equals(confirm_pwd)) {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
		else {
			((HttpServletResponse) response).sendRedirect("/BankingApplication/PasswordMismatch_ChangePassword.html");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
