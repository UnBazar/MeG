package org.meg.model;

public class Administrator {
	private String name;
	private String email;
	private String userName;
	private String password;
	
	/*
	 * The password needs to be at least 6 characters and less than 20
	 * The username & name need to be at least 3 characters and less than 25
	 */
	
	static final int MIN_PASS_LENGTH = 6;
	static final int MAX_PASS_LENGTH = 20;
	static final int MIN_USER_LENGTH = 3;
	static final int MAX_USER_LENGTH = 25;
	static final int MIN_NAME_LENGTH = 3;
	static final int MAX_NAME_LENGTH = 25;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if (name == null || name.length() < MIN_NAME_LENGTH ||  name.length() > MAX_NAME_LENGTH){
			throw new IllegalArgumentException("Invalid name!");
		}
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		if (password == null || password.length() < MIN_PASS_LENGTH || password.length() > MAX_PASS_LENGTH) {
			throw new IllegalArgumentException("Invalid password!");
		}
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email == null){
			throw new IllegalArgumentException("Invalid email");
		}
		int numberOfSpecialCharacter = 0;
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@'){
				numberOfSpecialCharacter++;
			}
		}
		if (numberOfSpecialCharacter != 1){
			throw new IllegalArgumentException("Invalid email!");
		}
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		if (userName == null || userName.length() < MIN_USER_LENGTH ||  userName.length() > MAX_USER_LENGTH) {
			throw new IllegalArgumentException("Invalid username!");
		}
		this.userName = userName;
	}	
}