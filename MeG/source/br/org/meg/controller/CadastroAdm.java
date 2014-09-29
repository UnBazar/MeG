package br.org.meg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.org.meg.dao.AdministradorDAO;
import br.org.meg.model.Administrador;

public class CadastroAdm implements Logica {
	
	public String executa(HttpServletRequest request, 
			HttpServletResponse response) {
		
		//Valida as entradas
		
		//Após validar
		Administrador adm = new Administrador();
		adm.setNome(request.getParameter("nome"));
		adm.setNomeDeUsuario(request.getParameter("nomeUsuario"));
		adm.setSenha(request.getParameter("senha"));
		adm.setEmail(request.getParameter("email"));
		
		AdministradorDAO dao = new AdministradorDAO();
		//persistir adm no banco
		dao.adicionar(adm);
		return "login.jsp";
	}
	
	private boolean validarSenha(String senha) {
		return senha.length() < 6 || senha.length() > 12;
	}
	
	private boolean validarEmail(String email) {
		return false;
	}
	
	private boolean validarNome(String nome) {
		return false;
	}
	
	private boolean validarNomeDeUsuario(String nomeDeUsuario) {
		return false;
	}
	
}
