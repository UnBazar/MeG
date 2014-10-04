package br.org.meg.exception;

public class DAOException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	String mensagem;
	
	public DAOException(Exception e) {
		this.mensagem = "Falha ao acessar o banco de dados! " + e.getMessage();
	}
	
	@Override
	public String getMessage() {
		return this.mensagem;
	}
}
