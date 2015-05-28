package org.meg.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * It's a Servlet class. Its function is called in /logout
 * This class executes the logout of an administrator
 */

@WebServlet("/logout")
public class AdministratorLogoutServlet extends HttpServlet {
	
	Logger logger = Logger.getLogger("AdministratorLogout");

	
	private static final long serialVersionUID = 1L;
	
	private final String LOGIN_VIEW = "login-adm.jsp";
	
	/**
	 * Clean session to logout, after this, return to login
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			// Invalidates session to logout
			request.getSession().invalidate();
			// Redirect to view of login
			request.getRequestDispatcher(LOGIN_VIEW).forward(request, response);
			logger.info("Administrator logged out.");
		} catch (ServletException | IOException servletException) {
			logger.error("Couldn't log out.");
			throw new RuntimeException("Falha ao deslogar!");
		}
	}
}
