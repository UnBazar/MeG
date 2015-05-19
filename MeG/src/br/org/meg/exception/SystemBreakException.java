package org.meg.exception;

import java.sql.Date;

import org.meg.dao.UtilDAO;
import org.meg.model.Error;

public class SystemBreakException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SystemBreakException(String message) {
		super(message);
		recordException(message);
	}
	
	/**
	 * Record error in database
	 * 
	 * @param message description of error
	 */
	private void recordException(String message) {
		Error erro = new Error();
		erro.setData(new Date(System.currentTimeMillis()));
		erro.setStatus(0);
		erro.setDescricao(message);
		UtilDAO dao = new UtilDAO();
		dao.registraErro(erro);
	}
}
