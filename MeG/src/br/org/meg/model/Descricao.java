package org.meg.model;

import org.meg.dao.UtilDAO;

public class Descricao {
	private int id;
	private String nome;

	public Descricao() {
		
	}
	
	public Descricao(int id){
		setId(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id > 5 || id < 1) {
			throw new IllegalArgumentException("ID da descrição inválido!");
		}
		UtilDAO dao = new UtilDAO();
		this.id = id;
		nome = dao.getNomeDescricao(id);
	}
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
		UtilDAO dao = new UtilDAO();
		id = dao.getIdDescricao(nome);
	}

}
