package org.meg.exception;

import java.sql.Date;

import org.meg.dao.UtilDAO;
import org.meg.model.Error;

public class UploadArquivoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	// String with the exception message
	private String message;
	
	public UploadArquivoException(String message) {
		this.message = "Fail to upload file: " + message;
		printStackTrace();
	}
	
	@Override
	public void printStackTrace() {
		Error error = new Error();
		error.setDate(new Date(System.currentTimeMillis()));
		error.setReferringClassName("Parser");
		error.setDescription(message);
		error.setStatus(0);
		UtilDAO dao = new UtilDAO();
		dao.registerError(error);
	}

}
