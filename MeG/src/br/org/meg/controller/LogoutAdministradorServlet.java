package org.meg.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutAdministradorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().invalidate();
		try {
			request.getRequestDispatcher("login-adm.jsp").forward(request, response);
		} catch (ServletException servletException) {
			System.err.println(servletException);
			throw new RuntimeException("falha ao deslogar!");
		} catch (IOException ioException) {
			System.err.println(ioException);
			throw new RuntimeException("falha ao deslogar!");
		}
	}
}
