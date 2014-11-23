package org.meg.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.meg.dao.AdministradorDAO;
import org.meg.model.Administrador;

@WebServlet("/login")
public class LoginAdministradorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Executa o login de um administrador
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AdministradorDAO dao = new AdministradorDAO();
		Administrador administrador = dao.buscaAdministrador(request.getParameter("nomeDeUsuario"), request.getParameter("senha"));
		if (administrador != null) {
			HttpSession sessao = request.getSession(true);
			sessao.setAttribute("administrador", administrador);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/administrador.jsp");
			requestDispatcher.forward(request, response);
		} else{
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login-adm.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}