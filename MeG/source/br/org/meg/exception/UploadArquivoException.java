package br.org.meg.exception;

public class UploadArquivoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String mensagem;
	
	public UploadArquivoException(Exception e) {
		this.mensagem = "Falha ao realizar o upload do arquivo! " + e.getMessage();
	}
	
	public UploadArquivoException(String mensagem) {
		this.mensagem = "Falha ao realizar o upload do arquivo! " + mensagem;
	}
	
	@Override
	public String getMessage() {
		return this.mensagem;
	}
}
