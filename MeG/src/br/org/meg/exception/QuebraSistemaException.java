package org.meg.exception;

import java.sql.Date;

import org.meg.dao.UtilDAO;
import org.meg.model.Erro;

public class QuebraSistemaException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String tipoExcecao = "QuebraDeSistema>>";
	private String mensagem;
	
	public QuebraSistemaException(String mensagem) {
		this.mensagem = mensagem;
		printStackTrace();
	}
	
	@Override
	public void printStackTrace() {
		Erro erro = new Erro();
		erro.setData(new Date(System.currentTimeMillis()));
		erro.setStatus(0);
		erro.setDescricao(tipoExcecao + mensagem);
		UtilDAO dao = new UtilDAO();
		dao.registraErro(erro);
	}
}
