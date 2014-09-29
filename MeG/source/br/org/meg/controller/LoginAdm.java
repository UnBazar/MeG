package br.org.meg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.org.meg.dao.AdministradorDAO;
import br.org.meg.model.Administrador;

public class LoginAdm implements Logica {
	
	public String executa(HttpServletRequest request,
			HttpServletResponse response) {
		//instancia adm que está tentando logar
		Administrador adm = new Administrador();
		adm.setNomeDeUsuario(request.getParameter("nomeUsuario"));
		adm.setSenha(request.getParameter("senha"));
		
		//verifica se o adm é válido
		AdministradorDAO dao = new AdministradorDAO();
		dao.buscar(adm);
		return "home.jsp";
	}
}
