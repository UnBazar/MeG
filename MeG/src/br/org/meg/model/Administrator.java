package org.meg.model;

public class Administrator {
	private String name;
	private String email;
	private String userName;
	private String password;
	
	public String getName() {
		return name;
	}
	
	public void setNome(String name) {
		if (name == null || name.length() < 3){
			throw new IllegalArgumentException("Nome inválido!");
		}
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setSenha(String password) {
		if (password == null || password.length() < 6 || password.length() > 20) {
			throw new IllegalArgumentException("Senha inválida!");
		}
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email == null){
			throw new IllegalArgumentException("Email inválido!");
		}
		int numberOfSpecialCharacter = 0;
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@'){
				numberOfSpecialCharacter++;
			}
		}
		if (numberOfSpecialCharacter != 1){
			throw new IllegalArgumentException("Email inválido!");
		}
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setNomeDeUsuario(String userName) {
		if (userName == null || userName.length() < 3) {
			throw new IllegalArgumentException("Nome de usuário inválido!");
		}
		this.userName = userName;
	}
	
}
