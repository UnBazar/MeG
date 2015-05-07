package org.meg.model;

import org.meg.dao.UtilDAO;
import org.meg.exception.SystemBreakException;

public class Description {
	private int id;
	private String nome;

	public Description() {
		
	}
	
	public Description(int id){
		setId(id);
	}
	
	public Description(String titulo){
		setNome(titulo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id > 5 || id < 1) {
			throw new SystemBreakException("Um id invalido da descricao foi inserido!");
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
