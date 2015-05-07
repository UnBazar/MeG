package org.meg.model;

import org.meg.dao.UtilDAO;
import org.meg.exception.SystemBreakException;

public class Section {
	private int id;
	private String nome;
	
	public Section() {
		
	}
	
	public Section(int id) {
		this.setId(id);
	}
	
	public Section(String nome){
		this.setNome(nome);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		if (id < 1 || id > 21) {
			throw new SystemBreakException("Um id invalido de Secao foi inserido!");
		}
		UtilDAO dao = new UtilDAO();
		this.id = id;
		nome = dao.getNomeSecao(id);
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		if (nome == null){
			throw new IllegalArgumentException("Nome da seção inválido!");
		}
		UtilDAO dao = new UtilDAO();
		this.nome = nome;
		id = dao.getIdSecao(nome);
	}	
}
