package org.meg.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.meg.dao.AdministratorDAO;
import org.meg.model.Administrator;
/**
 * It's a Servlet class. Its function is called in /login
 * This class validates the login of an administrator
 * or redirects to login
 */
@WebServlet("/login")
public class AdministratorLoginServlet extends HttpServlet {
	
	Logger logger = Logger.getLogger("AdministratorLogin");

	private static final long serialVersionUID = 1L;
	
	private final String TO_LOGIN = "login-adm.jsp";
	private final String ADMINISTRATOR_VIEW = "WEB-INF/jsp/administrador.jsp";


	/**
	 * Redirects to view of login
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher;
		HttpSession session = request.getSession();
		Administrator administrator = (Administrator) session.getAttribute("administrador");
		if(administrator != null) {
			requestDispatcher = request.getRequestDispatcher(ADMINISTRATOR_VIEW);
		} else {
			requestDispatcher = request.getRequestDispatcher(TO_LOGIN);
		}
		requestDispatcher.forward(request, response);
	}
	
	/**
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// Instantiate DAO to find an administrator
		AdministratorDAO administratorDAO = new AdministratorDAO();
		Administrator administrator = administratorDAO.searchAdministrator(request.getParameter("nomeDeUsuario"), request.getParameter("senha"));
		// Create requestDispatcher
		RequestDispatcher requestDispatcher;
		// If authentication was an success
		if (administrator != null) {
			// Gets session to put administrator
			HttpSession session = request.getSession(true);
			session.setAttribute("administrador", administrator);
			// redirects to administrator's view
			requestDispatcher = request.getRequestDispatcher(ADMINISTRATOR_VIEW);
			logger.info("Logged in as administrator.");
		} else {
			// If authentication return error
			requestDispatcher = request.getRequestDispatcher(TO_LOGIN);
			logger.info("Couldn't log in. Administrator null.");
		}
		requestDispatcher.forward(request, response);
	}
}