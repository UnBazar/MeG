package br.org.meg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.org.meg.dao.AdministradorDAO;
import br.org.meg.model.Administrador;

public class CadastroAdm implements Logica {
	
	public String executa(HttpServletRequest request, 
			HttpServletResponse response) {
		
		if (validarSenha(request.getParameter("senha"), request.getParameter("confirmacao")))
				throw new IllegalArgumentException("Senha inválida!");
		
		if (validarEmail(request.getParameter("email")))
				throw new IllegalArgumentException("Email inválido!");
		
		if (validarNome(request.getParameter("nome")))
				throw new IllegalArgumentException("Nome inválido!");
		
		if (validarNomeDeUsuario(request.getParameter("nomeUsuario")))
				throw new IllegalArgumentException("Nome de usuário inválido!");
		
		Administrador adm = new Administrador();
		adm.setNome(request.getParameter("nome"));
		adm.setNomeDeUsuario(request.getParameter("nomeUsuario"));
		adm.setSenha(request.getParameter("senha"));
		adm.setEmail(request.getParameter("email"));
		
		AdministradorDAO dao = new AdministradorDAO();

		dao.adicionar(adm);
		return "login-adm.jsp";
	}
	
	private boolean validarSenha(String senha, String confirmacao) {
		return (senha != null && (senha.length() >= 6 && senha.length() <= 12) &&
				(senha.equals(confirmacao)));
	}
	
	private boolean validarEmail(String email) {
		if (email == null) return false;
		String tokens1[] = email.split("@");
		String tokens2[] = tokens1[1].split(".");
		boolean emailValido = true;
		if (tokens1.length != 2 || tokens1[0].length() < 3 ||
				tokens2.length != 2) emailValido = false;
		
		return emailValido;
	}
	
	private boolean validarNome(String nome) {
		return (nome == null || nome.length() < 3);
	}
	
	private boolean validarNomeDeUsuario(String nomeDeUsuario) {
		if (nomeDeUsuario == null || nomeDeUsuario.length() < 3) return false;
		AdministradorDAO dao = new AdministradorDAO();
		return dao.existeNomeDeUsuario(nomeDeUsuario);
	}
	
}
