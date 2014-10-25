package br.org.meg.exception;

import java.sql.Date;

import br.org.meg.dao.UtilDAO;
import br.org.meg.model.Erro;

public class DAOException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String mensagem;
	private String nomeDaClasse;
	
	public DAOException() {
		this.mensagem = "DAOException executada! Alguma operação de banco falhou";
	}
	
	public DAOException(String mensagem, String nomeDaClasse) {
		this.mensagem = mensagem;
		this.nomeDaClasse = nomeDaClasse;
	}

	public DAOException(String mensagem) {
		this.mensagem = mensagem;
	}
	
	@Override
	public String getMessage() {
		return this.mensagem;
	}
	
	@Override
	public void printStackTrace() {
		Erro erro = new Erro();
		erro.setData(new Date(System.currentTimeMillis()));
		erro.setNomeDaClasseReferente(nomeDaClasse);
		erro.setDescricao(mensagem);
		erro.setStatus(0);
		UtilDAO dao = new UtilDAO();
		dao.registraErro(erro);
	}
}