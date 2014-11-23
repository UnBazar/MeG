package org.meg.exception;

import java.sql.Date;

import org.meg.dao.UtilDAO;
import org.meg.model.Erro;

public class DAOException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String mensagem;
	private String nomeDaClasse;
	
	public DAOException(String mensagem, String nomeDaClasse) {
		this.mensagem = mensagem;
		this.nomeDaClasse = nomeDaClasse;
		printStackTrace();
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