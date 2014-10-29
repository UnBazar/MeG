package org.meg.exception;

public class DAOException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	String mensagem;
	
	public DAOException(Exception e) {
		this.mensagem = "Falha ao acessar o banco de dados! " + e.getMessage();
	}
	
	public DAOException(String mensagem) {
		this.mensagem = mensagem;
	}
	
	@Override
	public String getMessage() {
		return this.mensagem;
	}
}
