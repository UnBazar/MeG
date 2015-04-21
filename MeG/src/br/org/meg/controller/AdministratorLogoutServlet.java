package org.meg.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * It's a Servlet class. Its function is called in /logout
 * This class executes the logout of an administrator
 */

@WebServlet("/logout")
public class AdministratorLogoutServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) {
		
		request.getSession().invalidate();
		
		try {
			request.getRequestDispatcher("login-adm.jsp").forward(request, response);
			
		} catch (ServletException | IOException servletException) {
			throw new RuntimeException("Falha ao deslogar!");
		}
	}
}
