package br.org.meg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.org.meg.dao.AdministradorDAO;
import br.org.meg.model.Administrador;

public class CadastroAdm implements Logica {
	
	public String executa(HttpServletRequest request, 
			HttpServletResponse response) {

		if (!validarSenha(request.getParameter("senha"), request.getParameter("confirmacao")))
				throw new IllegalArgumentException("Senha inválida!");
		
		if (!validarNomeDeUsuario(request.getParameter("nomeDeUsuario")))
				throw new IllegalArgumentException("Nome de usuário inválido!");
		
		Administrador adm = new Administrador();
		adm.setNome(request.getParameter("nome"));
		adm.setNomeDeUsuario(request.getParameter("nomeDeUsuario"));
		adm.setSenha(request.getParameter("senha"));
		adm.setEmail(request.getParameter("email"));
		
		AdministradorDAO dao = new AdministradorDAO();

		dao.adicionar(adm);
		return "login-adm.jsp";
	}
	
	private boolean validarSenha(String senha, String confirmacao) {
		return (senha.equals(confirmacao));
	}
	
	private boolean validarNomeDeUsuario(String nomeDeUsuario) {
		AdministradorDAO dao = new AdministradorDAO();
		return !dao.existeNomeDeUsuario(nomeDeUsuario);
	}
	
}
