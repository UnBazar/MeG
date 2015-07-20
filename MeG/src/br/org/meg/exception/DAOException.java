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
	// Error got
	private Error error = new Error();
	
	public DAOException(String message, String nameOfClass) {
		this.message = message;
		this.nameOfClass = nameOfClass;
		printStackTrace();
	}
	
	/**
	 * Record error in database
	 */
	public void recordError() {
		error.setDate(new Date(System.currentTimeMillis()));
		error.setReferringClassName(nameOfClass);
		error.setDescription(message);
		error.setStatus(0);
		UtilDAO dao = new UtilDAO();
		dao.registerError(error);
	}
}