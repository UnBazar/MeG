package org.meg.model;

public class Noticia {

	private int id;
	private String noticia;
	private String imagem;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNoticia() {
		return noticia;
	}
	public void setNoticia(String noticia) {
		if (noticia == null || noticia.length() > 200) {
			throw new IllegalArgumentException("Noticia inválida!");
		}
		this.noticia = noticia;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		if (imagem == null || imagem.length() > 255) {
			throw new IllegalArgumentException("Url da Imagem inválida!");
		}
		this.imagem = imagem;
	}	
}