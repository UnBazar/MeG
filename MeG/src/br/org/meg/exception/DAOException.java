package org.meg.exception;

import java.sql.Date;

import org.meg.dao.UtilDAO;
import org.meg.model.Error;

/**
 * Treat and register errors to manage them
 */
public class DAOException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	// Message of error
	private String message;
	// Name of class that throws this exception
	private String nameOfClass;
	// Error getted
	private Error error = new Error();
	
	public DAOException(String message, String nomeDaClasse) {
		this.message = message;
		this.nameOfClass = nomeDaClasse;
		printStackTrace();
	}
	
	/**
	 * Record error in database
	 */
	public void recordError() {
		error.setData(new Date(System.currentTimeMillis()));
		error.setNomeDaClasseReferente(nameOfClass);
		error.setDescricao(message);
		error.setStatus(0);
		UtilDAO dao = new UtilDAO();
		dao.registraErro(error);
	}
}