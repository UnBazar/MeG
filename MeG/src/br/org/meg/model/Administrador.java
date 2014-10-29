package org.meg.model;

public class Administrador {
	private String nome;
	private String email;
	private String nomeDeUsuario;
	private String senha;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		if (nome == null || nome.length() < 3){
			throw new IllegalArgumentException("Nome inválido!");
		}
		this.nome = nome;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		if (senha == null || senha.length() < 6 || senha.length() > 20) {
			throw new IllegalArgumentException("Senha inválida!");
		}
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email == null){
			throw new IllegalArgumentException("Email inválido!");
		}
		int numeroDeArrobas = 0;
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@'){
				numeroDeArrobas++;
			}
		}
		if (numeroDeArrobas != 1){
			throw new IllegalArgumentException("Email inválido!");
		}
		this.email = email;
	}

	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}

	public void setNomeDeUsuario(String nomeDeUsuario) {
		if (nomeDeUsuario == null || nomeDeUsuario.length() < 3) {
			throw new IllegalArgumentException("Nome de usuário inválido!");
		}
		this.nomeDeUsuario = nomeDeUsuario;
	}
	
}
