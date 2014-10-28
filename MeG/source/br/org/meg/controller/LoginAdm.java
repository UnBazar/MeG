package br.org.meg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.org.meg.dao.AdministradorDAO;
import br.org.meg.model.Administrador;

public class LoginAdm implements Logica {
	
	/**
	 * Executa o login de um administrador
	 */
	public String executa(HttpServletRequest request,
			HttpServletResponse response) {
		AdministradorDAO dao = new AdministradorDAO();
		Administrador administrador = dao.buscaAdministrador(request.getParameter("nomeDeUsuario"), request.getParameter("senha"));
		if (administrador != null) {
			HttpSession sessao = request.getSession(true);
			sessao.setAttribute("adm", administrador);
			return "/WEB-INF/jsp/home.jsp";
		} else{
			return "login-adm.jsp";
		}
	}
}