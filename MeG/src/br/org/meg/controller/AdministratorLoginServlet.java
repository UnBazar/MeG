package org.meg.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.meg.dao.AdministratorDAO;
import org.meg.model.Administrator;
/**
 * It's a Servlet class. Its function is called in /login
 * This class validates the login of an administrator
 */
@WebServlet("/login")
public class AdministratorLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private final String SAME_VIEW = "login-adm.jsp";
	private final String ADMINISTRATOR_VIEW = "WEB-INF/jsp/administrador.jsp";


	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// Instantiate DAO to find an administrator
		AdministratorDAO dao = new AdministratorDAO();
		Administrator administrator;
		administrator = dao.searchAdministrator(request.getParameter("nomeDeUsuario"), 
												request.getParameter("senha"));
		
		if (administrator != null) {
			
			/*
			 *  If searchAdministrator finds an administrator, 
			 *  Request redirects to administrador.jsp 
			 */
			HttpSession sessao = request.getSession(true);
			sessao.setAttribute("administrador", administrator);
			RequestDispatcher requestDispatcher;
			requestDispatcher = request.getRequestDispatcher(ADMINISTRATOR_VIEW);
			requestDispatcher.forward(request, response);
			
		} else{

			// Else redirects to the same page.
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(SAME_VIEW);
			requestDispatcher.forward(request, response);
		}
	}
}