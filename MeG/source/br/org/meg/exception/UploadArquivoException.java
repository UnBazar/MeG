package br.org.meg.exception;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class UploadArquivoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String mensagem;
	
	public UploadArquivoException() {
		this.mensagem = "Falha ao realizar o upload do arquivo!";
	}
	
	public UploadArquivoException(String mensagem) {
		this.mensagem = mensagem;
	}
	
	@Override
	public String getMessage() {
		return this.mensagem;
	}
	
	@Override
	public void printStackTrace() {
		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 'T' HH:mm");
		this.mensagem += " na data " + sdf.format(data);
		System.out.println(this.mensagem);
	}
}
