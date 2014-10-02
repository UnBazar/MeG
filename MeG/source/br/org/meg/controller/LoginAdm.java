package br.org.meg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.org.meg.dao.AdministradorDAO;
import br.org.meg.model.Administrador;


public class LoginAdm implements Logica {
	
	public String executa(HttpServletRequest request,
			HttpServletResponse response) {
		AdministradorDAO dao = new AdministradorDAO();
		Administrador adm = dao.validaLogin(request.getParameter("nomeDeUsuario"), request.getParameter("senha"));
		if (adm != null) {
			HttpSession sessao = request.getSession(false);
			sessao.setAttribute("adm", adm);
			return "home.jsp";
		} else return "login-adm.jsp";
	}
}
