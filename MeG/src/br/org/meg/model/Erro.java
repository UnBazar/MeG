package org.meg.model;

import java.sql.Date;

public class Erro {
	private int id;
	private String descricao;
	private String nomeDaClasseReferente;
	private Date data;
	/* Codigo de status do erro 
	 * 0 para não verificado
	 * 1 para verificacao em progresso 
	 * 2 para verificado
	 */
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNomeDaClasseReferente() {
		return nomeDaClasseReferente;
	}

	public void setNomeDaClasseReferente(String nomeDaClasseReferente) {
		this.nomeDaClasseReferente = nomeDaClasseReferente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}