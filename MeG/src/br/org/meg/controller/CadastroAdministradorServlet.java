package org.meg.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.meg.dao.AdministradorDAO;
import org.meg.model.Administrador;

@WebServlet("/cadastro")
public class CadastroAdministradorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
/**
 * @throws IOException 
 * @throws ServletException 
 * 
 */
	public void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		try {
			if (!validarSenha(request.getParameter("senha"), request.getParameter("confirmacao"))){
				throw new IllegalArgumentException("Senha inválida!");
			}		
			if (!validarNomeDeUsuario(request.getParameter("nomeDeUsuario"))){
				throw new IllegalArgumentException("Nome de usuário inválido!");
			}
			Administrador administrador = new Administrador();
			administrador.setNome(request.getParameter("nome"));
			administrador.setNomeDeUsuario(request.getParameter("nomeDeUsuario"));
			administrador.setSenha(request.getParameter("senha"));
			administrador.setEmail(request.getParameter("email"));
			AdministradorDAO dao = new AdministradorDAO();
			dao.adicionar(administrador);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login-adm.jsp");
			requestDispatcher.forward(request, response);
		} catch (IllegalArgumentException exception) {
			if (exception.getMessage().equals("Senha inválida!")) {
				request.setAttribute("senhaInvalida", true);
				request.getRequestDispatcher("cadastro-adm.jsp").
				forward(request, response);
			}
			if (exception.getMessage().equals("Nome de usuário inválido!")) {
				request.setAttribute("nomeInvalido", true);
				request.getRequestDispatcher("cadastro-adm.jsp").
				forward(request, response);
			}
		}
	}
	
	private boolean validarSenha(String senha, String confirmacao) {
		return (senha.equals(confirmacao));
	}
	
	private boolean validarNomeDeUsuario(String nomeDeUsuario) {
		AdministradorDAO dao = new AdministradorDAO();
		return !dao.existeNomeDeUsuario(nomeDeUsuario);
	}
	
}
