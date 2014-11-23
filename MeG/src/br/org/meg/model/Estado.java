package org.meg.model;

import org.meg.dao.UtilDAO;
import org.meg.exception.QuebraSistemaException;

public class Estado {
	private int id;
	private String nome;
	private String sigla;
	
	public Estado() {
	
	}

	public Estado(int idEstado) {
		setId(idEstado);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id < 1 || id > 27){
			throw new QuebraSistemaException("Um id invalido de Estado foi inserido!");
		}
		this.id = id;
		UtilDAO dao = new UtilDAO();
		sigla = dao.getSiglaEstado(id);
		nome = dao.getNomeEstado(id);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome == null) {
			throw new IllegalArgumentException("Nome do estado inválido!");
		}
		UtilDAO dao = new UtilDAO();
		this.nome = nome;
		id = dao.getIdEstado(nome);
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}
